package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.User;
import com.xihu.conference.xihu.mapper.UserMapper;
import com.xihu.conference.xihu.service.UserService;
import com.xihu.conference.xihu.utils.MD5Utils;
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

    @Autowired
    private UserMapper userMapper;

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

}
