package com.xihu.conference.xihu.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xihu.conference.xihu.constant.MessageConstant;
import com.xihu.conference.xihu.dto.UserLoginDTO;
import com.xihu.conference.xihu.entity.User;
import com.xihu.conference.xihu.exception.LoginFailedException;
import com.xihu.conference.xihu.mapper.UserMapper;
import com.xihu.conference.xihu.properties.WeChatProperties;
import com.xihu.conference.xihu.service.UserService;
import com.xihu.conference.xihu.utils.HttpClientUtil;
import com.xihu.conference.xihu.utils.MD5Utils;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    private static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WeChatProperties weChatProperties;

    @Override
    public void sign(Long id) {
        userMapper.sign(id);
    }

    @Override
    public void insertOne(User user) {
        user.setPassword(MD5Utils.MD5(user.getPassword()));
        userMapper.insertOne(user);
    }

    @Override
    public void deleteOne(Long id) {
        userMapper.deleteOne(id);
    }

    @Override
    public void deleteByName(String name) {
        userMapper.deleteByName(name);
    }

    @Override
    public void updateOne(User user) {
        userMapper.updateOne(user);
    }

    @Override
    public void consumeSome(Integer integral,Long id) {
        userMapper.consumeSome(integral,id);
    }

    @Override
    public Integer getIntegral(Long integralId) {
        return userMapper.getIntegral(integralId);
    }

    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public User selectWhenLogin(User user) {
        return userMapper.selectWhenLogin(user);
    }

    @Override
    public void updateImage(String imageUrl, Long id) {
        userMapper.updateImage(imageUrl,id);
    }

    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        //获取openid
        String openid = getOpenid(userLoginDTO.getCode());
        //判断openid是否为空，若为空则抛出业务异常
        if (openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        //判断用户是否注册过
        User user = userMapper.getByOpenid(openid);
        //是新用户则自动完成注册
        if (user==null){
            user = User.builder()
                .openid(openid)
                .build();
            userMapper.insertWithOpenId(user);
        }


        return user;
    }

    /**
     * 调用微信接口获取openid
     *
     * @param code
     * @return
     */
    private String getOpenid(String code) {
        //调用微信接口获取openid
        Map<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        //获取返回的json字符串
        String json = HttpClientUtil.doGet(WX_LOGIN, map);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");

        return openid;
    }

}
