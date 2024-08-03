package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Agenda;
import java.sql.Timestamp;
import java.util.List;
import java.util.Timer;
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
public interface AgendaMapper {

    @Insert("insert into agenda(title, tag, content_url, sub_title, start_time, end_time, timespan, location, is_live, resource_id) "
        + "values (#{title},#{tag},#{contentUrl},#{subTitle},#{startTime},#{endTime},#{timespan},#{location},#{isLive},#{resourceId})")
    void insertOne(Agenda agenda);

    @Update("update agenda set is_delete=1 where id =#{id}")
    void deleteById(Long id);

    @Update("update agenda set is_live=1  where id=#{id}")
    void changeStatus2Live(Long id);

    @Update("update agenda set is_live=0 where id=#{id}")
    void changeStatus2playback(Long id);

    @Update("update agenda set title=#{title},tag=#{tag},content_url=#{contentUrl},sub_title=#{subTitle},start_time=#{startTime},end_time=#{endTime},"
        + "timespan=#{timespan},location=#{location},is_live=#{isLive},resource_id=#{resourceId} where id=#{id}")
    void updateOneById(Agenda agenda);

    List<Agenda> selectByName(String name);

    @Select("select * from agenda where id=#{id} and is_delete=0")
    Agenda selectById(Long id);

    @Select("select * from agenda where tag=#{tag} and is_delete=0")
    List<Agenda> selectByTag(String tag);

    @Select("select * from agenda where is_delete=0")
    List<Agenda> selectAll();

    @Select("select distinct tag from agenda")
    List<String> showTags();

    @Select("select start_time from agenda")
    List<Timestamp> showDates();

    @Select("select * from agenda where start_time >= #{beginTime} and end_time <= #{endTime} and is_delete=0")
    List<Agenda> selectByDate(String beginTime, String endTime);

    @Update("update agenda set like_count=like_count+1 where id=#{likedId}")
    void addLikeNum(Long likedId);

    @Update("update agenda set like_count=like_count-1 where id=#{likedId}")
    void subLikeNum(Long likedId);
}
