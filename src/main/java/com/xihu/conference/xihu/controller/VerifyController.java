package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.entity.User;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.utils.SMSUtils;
import com.xihu.conference.xihu.utils.ValidateCodeUtils;
import com.xihu.conference.xihu.utils.VerifyUtil;
import io.swagger.annotations.ApiOperation;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 13759
 */
@Slf4j
@RestController
@RequestMapping("/verify")
public class VerifyController {

    @Autowired
    private SMSUtils smsUtils;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 生成验证码的接口
     *
     * @param response Response对象
     * @param request  Request对象
     * @throws Exception
     */
    @PostMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) {
        // 获取到session
        HttpSession session = request.getSession();
        // 取到sessionid
        String id = session.getId();

        // 利用图片工具生成图片
        // 返回的数组第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyUtil.newBuilder()
            .setWidth(120)   //设置图片的宽度
            .setHeight(35)   //设置图片的高度
            .setSize(4)      //设置字符的个数
            .setLines(5)    //设置干扰线的条数
            .setFontSize(30) //设置字体的大小
            .setTilt(true)   //设置是否需要倾斜
            .setBackgroundColor(Color.LIGHT_GRAY) //设置验证码的背景颜色
            .build()         //构建VerifyUtil项目
            .createImage();  //生成图片
        // 将验证码存入Session
        session.setAttribute("SESSION_VERIFY_CODE_" + id, objs[0]);
        // 打印验证码

        // 在redis中保存一个验证码最多尝试次数
        // 这里采用的是先预设一个上限次数，再以reidis decrement(递减)的方式来进行验证
        // 这样有个缺点，就是用户只申请验证码，不验证就走了的话，这里就会白白占用5分钟的空间，造成浪费了
        // 为了避免以上的缺点，也可以采用redis的increment（自增）方法，只有用户开始在做验证的时候设置值，
        //    超过多少次错误，就失效；避免空间浪费
        redisTemplate.opsForValue().set(("VERIFY_CODE_" + id), 3, 5 * 60, TimeUnit.SECONDS);

        // 将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        try {
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 业务接口包含了验证码的验证
     *
     * @param code    前端传入的验证码
     * @param request Request对象
     * @return
     */
    @GetMapping("/checkCode")
    public Result checkCode(@RequestParam String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = session.getId();

        // 将redis中的尝试次数减一
        String verifyCodeKey = "VERIFY_CODE_" + id;
        long num = redisTemplate.opsForValue().decrement(verifyCodeKey);

        // 如果次数次数小于0 说明验证码已经失效
        if (num < 0) {
            return Result.error("验证码失效!");
        }

        // 将session中的取出对应session id生成的验证码
        String serverCode = (String) session.getAttribute("SESSION_VERIFY_CODE_" + id);
        // 校验验证码
        if (null == serverCode || null == code || !serverCode.toUpperCase().equals(code.toUpperCase())) {
            return Result.error("验证码错误!");
        }

        // 验证通过之后手动将验证码失效
        redisTemplate.delete(verifyCodeKey);

        // 这里做具体业务相关

        return Result.success("验证码正确!");
    }

    @ApiOperation("发送短信")
    @PostMapping("/sendMsg")
    public Result<String> sendMsg(@RequestBody User user) {
        //1.获取手机号
        String phone = user.getTel();

        if (StringUtils.isEmpty(phone)) {
            return Result.error("短信发送失败");
        }
        //2.随机生成四位验证码
        String code = ValidateCodeUtils.generateValidateCode(4).toString();

        //3.调用阿里云提供的短信服务
        smsUtils.sendMessage("西湖论剑", "SMS_465314318", phone, code);

        //4.需要将生成的验证码保存到redis中
        redisTemplate.opsForValue().set("SMS_"+ phone,Integer.valueOf(code),300,TimeUnit.SECONDS);

        return Result.success("验证码短信发送成功");
    }
}