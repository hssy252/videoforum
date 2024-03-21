package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.Subscription;
import com.xihu.conference.xihu.mapper.SubscriptionMapper;
import com.xihu.conference.xihu.service.SubscriptionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    @Override
    public void insertOne(Long userId, Long matterId, Short type) {
        subscriptionMapper.insertOne(userId,matterId,type);
    }

    @Override
    public void deleteOne(Long userId,Long matterId,Short type) {
        subscriptionMapper.deleteOne(userId,matterId,type);
    }

    @Override
    public void updateOne(Subscription subscription) {

    }

    //同时要返回对应会议或活动的细则
    @Override
    public Subscription selectOne(Long userId, Long matterId, Short type) {
        return subscriptionMapper.selectOne(userId,matterId,type);
    }



    //同时要返回对应会议或活动的细则
    @Override
    public List<Subscription> selectAll(Long userId, Short type) {
        return subscriptionMapper.selectAll(userId,type);
    }

    @Override
    public Subscription checkIfExist(Long userId, Long agendaId, Short type) {
        return subscriptionMapper.checkIfExist(userId,agendaId,type);
    }

    @Override
    public void subscriptionOne(Long userId, Long agendaId, Short type) {
        subscriptionMapper.subscribeOne(userId,agendaId,type);
    }
}
