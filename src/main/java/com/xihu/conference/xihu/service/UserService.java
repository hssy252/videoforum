package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.dto.UserLoginDTO;
import com.xihu.conference.xihu.entity.User;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface UserService {

    void sign(Long id);

    void insertOne(User user);

    void deleteOne(Long id);

    void deleteByName(String name);

    void updateOne(User user);

    /**
     * 用户兑换商品后需要更新其积分
     * @param integral 商品积分总数
     * @param id 用户的id
     */
    void consumeSome(Integer integral,Long id);

    /**
     * 获取积分
     * @param integralId 对应积分行为的id
     */
    Integer getIntegral(Long integralId);

    User selectById(Long id);

    User selectByName(String name);

    User selectWhenLogin(User user);

    void updateImage(String imageUrl, Long id);

    User wxLogin(UserLoginDTO userLoginDTO);

    User selectByTel(String tel);

    void insertOneWithTel(User user);

    void insertOneWithTelAndName(User appUser);
}
