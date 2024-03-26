package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.entity.Subscription;
import com.xihu.conference.xihu.vo.SubscriptionActivityVO;
import com.xihu.conference.xihu.vo.SubscriptionAgendaVO;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface SubscriptionService {

    void insertOne(Long userId,Long matterId,Short type);

    void deleteOne(Long userId,Long matterId,Short type);

    void updateOne(Subscription subscription);

    Subscription selectOne(Long userId,Long matterId,Short type);

    List<SubscriptionAgendaVO> selectAllAgendas(Long userId);

    Subscription checkIfExist(Long userId, Long agendaId, Short type);

    void subscriptionOne(Long userId, Long agendaId, Short type);

    List<SubscriptionActivityVO> selectAllActivities(Long userId);
}
