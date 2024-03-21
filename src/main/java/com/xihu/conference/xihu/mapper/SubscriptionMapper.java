package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Subscription;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Mapper
public interface SubscriptionMapper {

    @Insert("insert into subscription(user_id, matter_id, type) VALUES(#{userId},#{matterId},#{type})")
    void insertOne(Long userId, Long matterId, Short type);

    @Update("update subscription set is_delete=1 where user_id=#{userId} and matter_id=#{matterId} and type=#{type}")
    void deleteOne(Long userId, Long matterId, Short type);

    @Select("select * from subscription where user_id=#{userId} and type=#{type} and is_delete=0 ")
    List<Subscription> selectAll(Long userId, Short type);

    @Select("select * from subscription where user_id=#{userId} and matter_id=#{matterId} and type=#{type} and is_delete=0")
    Subscription selectOne(Long userId, Long matterId, Short type);

    @Select("select * from subscription where user_id=#{userId} and matter_id=#{agendaId} and type=#{type} and is_delete=1")
    Subscription checkIfExist(Long userId, Long agendaId, Short type);

    @Update("update subscription set is_delete=0 where user_id=#{userId} and matter_id=#{agendaId} and type=#{type}")
    void subscribeOne(Long userId, Long agendaId, Short type);
}
