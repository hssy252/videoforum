package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.constant.JwtClaimsConstant;
import com.xihu.conference.xihu.constant.OssConstant;
import com.xihu.conference.xihu.dto.UserCodeLoginDTO;
import com.xihu.conference.xihu.dto.UserLoginDTO;
import com.xihu.conference.xihu.dto.WxAppLoginDTO;
import com.xihu.conference.xihu.entity.User;
import com.xihu.conference.xihu.properties.JwtProperties;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.UserService;
import com.xihu.conference.xihu.utils.AliOssUtil;
import com.xihu.conference.xihu.utils.JwtUtil;
import com.xihu.conference.xihu.utils.MD5Utils;
import com.xihu.conference.xihu.vo.UserLoginVO;
import com.xihu.conference.xihu.vo.WxAppLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private AliOssUtil aliOssUtil;


    @ApiOperation("用户注册")
    @PostMapping("/code")
    public Result register(@RequestBody UserCodeLoginDTO user, HttpServletRequest request) {

        //先验证图形验证码
        String code = user.getCode();
        HttpSession session = request.getSession();

        String id = session.getId();
        // 将redis中的尝试次数减一
        String verifyCodeKey = "VERIFY_CODE_" + id;
        long num = redisTemplate.opsForValue().decrement(verifyCodeKey);

        // 如果次数次数小于0 说明验证码已经失效
        if (num < 0) {
            return Result.error("图形验证码失效!");
        }

        // 将session中的取出对应session id生成的验证码
        String serverCode = (String) session.getAttribute("SESSION_VERIFY_CODE_" + id);
        // 校验验证码
        if (null == serverCode || null == code || !serverCode.toUpperCase().equals(code.toUpperCase())) {
            return Result.error("图形验证码错误!");
        }

        // 图形验证通过之后手动将图形验证码失效
        redisTemplate.delete(verifyCodeKey);

        //图形验证码正确后的流程
        String SMScode = redisTemplate.opsForValue().get("SMS_" + user.getTel());
        if (SMScode == null) {
            return Result.error("短信验证码已过期");
        }

        //短信验证码未过期则判断是否正确,这里做具体业务相关
        if (user.getMessageCode().equals(SMScode)) {
            User newUser = userService.selectByTel(user.getTel());

            //手机号没有被占用
            Map<String, Object> claims = new HashMap<>();
            if (newUser == null) {

                User realUser = new User();
                realUser.setTel(user.getTel());
                userService.insertOneWithTel(realUser);
                //返回token信息
                claims.put("id", realUser.getId());
                String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
                //将token存到redis中，方用于校验时效性
                redisTemplate.opsForValue().set(token, token, 12, TimeUnit.HOURS);
                return Result.success(token);
            } else {
                //返回token信息
                claims.put("id", newUser.getId());
                String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
                //将token存到redis中，方用于校验时效性
                redisTemplate.opsForValue().set(token, token, 12, TimeUnit.HOURS);
                return Result.success(token);
            }
        } else {
            return Result.error("短信验证码错误");
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

    @ApiOperation("小程序用户登录,通过wx.login函数")
    @PostMapping("/wxAppLogin")
    public Result<UserLoginVO> wxAppLogin(@RequestBody UserLoginDTO userLoginDTO) {
        //微信登录
        User user = userService.wxLogin(userLoginDTO);

        Long id = user.getId();
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, id);
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        //将token存到redis中，方用于校验时效性
        redisTemplate.opsForValue().set(token, token, 12, TimeUnit.HOURS);

        UserLoginVO userLoginVO = UserLoginVO
            .builder()
            .userId(id)
            .openid(user.getOpenid())
            .token(token)
            .build();

        return Result.success(userLoginVO);
    }

    // TODO 对接后的wx小程序登录,待对接
    @ApiOperation("小程序直接登录")
    @PostMapping("/wxLogin")
    public Result<WxAppLoginVO> wxLogin(@RequestBody WxAppLoginDTO wxAppLoginDTO) {
        //查询是否有该用户
        String phone = wxAppLoginDTO.getPhone();
        User user = userService.selectByTel(phone);
        String token;
        Long id;
        String imgUrl = "";
        String nickName;
        String identification = "线上观众";
        if (user == null) {
            User appUser = User.builder()
                .tel(phone)
                .name(wxAppLoginDTO.getNickName())
                .build();
            userService.insertOneWithTelAndName(appUser);
            id = appUser.getId();
            nickName = wxAppLoginDTO.getNickName();
        } else {
            id = user.getId();
            imgUrl = user.getImage();
            nickName = user.getName();
            identification = user.getIdentification();
        }
        token = getToken(id);
        WxAppLoginVO wxAppLoginVO = WxAppLoginVO.builder()
            .userId(id)
            .token(token)
            .imgUrl(imgUrl)
            .name(nickName)
            .identification(identification)
            .build();
        //将token存到redis中，方用于校验时效性
        redisTemplate.opsForValue().set(token, token, 12, TimeUnit.HOURS);
        return Result.success(wxAppLoginVO);
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


    @ApiOperation("修改用户头像")
    @PostMapping("/img")
    public Result updateImage(MultipartFile file, Long id) throws IOException {
        String originalName = file.getOriginalFilename();
        String name = UUID.randomUUID().toString() + originalName.substring(originalName.lastIndexOf("."));
        String imageUrl = aliOssUtil.upload(file.getBytes(), OssConstant.USER_ICON_DIRECTORY+"/"+name);
        userService.updateImage(imageUrl, id);
        return Result.success(imageUrl);
    }

    private String getToken(Long id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, id);
        return JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
    }

}
