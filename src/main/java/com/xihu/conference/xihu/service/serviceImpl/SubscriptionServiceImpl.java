package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.Subscription;
import com.xihu.conference.xihu.mapper.ActivityMapper;
import com.xihu.conference.xihu.mapper.SubscriptionMapper;
import com.xihu.conference.xihu.service.SubscriptionService;
import com.xihu.conference.xihu.vo.SubscriptionActivityVO;
import com.xihu.conference.xihu.vo.SubscriptionAgendaVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    @Transactional
    public void insertOne(Long userId, Long matterId, Short type) {
        subscriptionMapper.insertOne(userId, matterId, type);
        if (type.equals(Short.valueOf("1"))) {
            activityMapper.addBookCount(matterId);
        }
    }

    @Override
    @Transactional
    public void deleteOne(Long userId, Long matterId, Short type) {
        subscriptionMapper.deleteOne(userId, matterId, type);
        if (type.equals(Short.valueOf("1"))){
            activityMapper.subBookCount(matterId);
        }
    }

    @Override
    public void updateOne(Subscription subscription) {

    }

    //同时要返回对应会议或活动的细则
    @Override
    public Subscription selectOne(Long userId, Long matterId, Short type) {
        return subscriptionMapper.selectOne(userId, matterId, type);
    }


    //同时要返回对应会议或活动的细则
    @Override
    public List<SubscriptionAgendaVO> selectAllAgendas(Long userId) {
        return subscriptionMapper.selectAllAgendas(userId);
    }

    @Override
    public Subscription checkIfExist(Long userId, Long agendaId, Short type) {
        return subscriptionMapper.checkIfExist(userId, agendaId, type);
    }

    @Override
    public void subscriptionOne(Long userId, Long matterId, Short type) {
        subscriptionMapper.subscribeOne(userId, matterId, type);
        if (type.equals(Short.valueOf("1"))) {
            activityMapper.addBookCount(matterId);
        }

    }

    @Override
    public List<SubscriptionActivityVO> selectAllActivities(Long userId) {
        return subscriptionMapper.selectAllActivities(userId);
    }
}
