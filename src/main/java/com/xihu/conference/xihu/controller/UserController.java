package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.constant.JwtClaimsConstant;
import com.xihu.conference.xihu.dto.UserLoginDTO;
import com.xihu.conference.xihu.entity.User;
import com.xihu.conference.xihu.properties.JwtProperties;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.UserService;
import com.xihu.conference.xihu.utils.JwtUtil;
import com.xihu.conference.xihu.utils.MD5Utils;
import com.xihu.conference.xihu.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    //TODO 测试阶段，后续采用扫码或者验证码
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        User newUser = userService.selectByName(user.getName());
        if (newUser == null) {//用户名没有被占用
            userService.insertOne(user);
            return Result.success();
        } else {
            return Result.error("该用户名已存在");
        }
    }


    @ApiOperation("用户登录")
    @PostMapping("/loginBypwd")
    public Result loginByPassword(@RequestBody User user) {
        User byName = userService.selectByName(user.getName());
        if (byName == null) {
            return Result.error("用户名不存在");
        }
        if (Objects.equals(byName.getPassword(), "")) {
            return Result.error("请设置密码");
        }

        if (Objects.equals(MD5Utils.MD5(byName.getPassword()), MD5Utils.MD5(user.getPassword()))) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getName());
            String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
            //将token存到redis中，方用于校验时效性
            redisTemplate.opsForValue().set(token, token, 12, TimeUnit.HOURS);
            return Result.success(token);
        } else {
            return Result.error("密码错误");
        }

    }

    @ApiOperation("小程序用户登录")
    @PostMapping("/wxAppLogin")
    public Result<UserLoginVO> wxAppLogin(@RequestBody UserLoginDTO userLoginDTO) {
        //微信登录
        User user = userService.wxLogin(userLoginDTO);

        Long id = user.getId();
        Map<String, Object> claims = new HashMap<>();
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        claims.put(JwtClaimsConstant.USER_ID, id);

        JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO
            .builder()
            .userId(id)
            .openid(user.getOpenid())
            .token(token)
            .build();

        return Result.success(userLoginVO);
    }


    //TODO 后期改成从jwt中获取用户id
    @ApiOperation("用户签到并增加积分")
    @GetMapping("/sign")
    public Result sign(@RequestParam Long id, @RequestParam String date) {
        String key = "user_" + id;
        if (Objects.equals(redisTemplate.opsForValue().get(key), date)) {
            return Result.error("已签到");
        } else {
            redisTemplate.opsForValue().set(key, date, 24, TimeUnit.HOURS);
            userService.sign(id);
            return Result.success("签到成功");
        }
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/userinfo")
    public Result<User> getInfo(@RequestParam Long id) {
        return Result.success(userService.selectById(id));
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/userinfo")
    public Result updateInfo(@RequestBody User user) {
        userService.updateOne(user);
        return Result.success();
    }

    //TODO 后续应该改为用户上传文件
    @ApiOperation("修改用户头像")
    @PutMapping("/img")
    public Result updateImage(String imageUrl, Long id) {
        userService.updateImage(imageUrl, id);
        return Result.success(imageUrl);
    }

}
