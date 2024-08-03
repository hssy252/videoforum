package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Activity;
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
public interface ActivityMapper {

    @Insert("insert into activity(name, column_name,content_type,content_id, cover, description,is_bookable,timespan) VALUES (#{name},#{columnName},#{contentType},#{contentId},#{cover},#{description},#{isBookable},#{timespan})")
    void insertOne(Activity activity);

    @Update("update activity set is_delete=1 where id=#{id}")
    void deleteById(Long id);

    @Update("update activity set name=#{name},column_name=#{columnName},content_type=#{contentType},content_id=#{contentId},cover=#{cover},description=#{description},is_bookable=#{isBookable},timespan=#{timespan} where id=#{id}")
    void updateOne(Activity activity);

    @Select("select distinct column_name from activity ")
    List<String> showTags();

    @Select("select * from activity where column_name=#{tag} and is_delete=0")
    List<Activity> selectByTags(String tag);

    @Select("select * from activity where is_delete=0")
    List<Activity> showAll();

    @Update("update activity set book_count=#{num} where id=#{id}")
    void updateBookCount(Long id,Integer num);

    @Update("update activity set watch_num=watch_num+1 where id=#{id}")
    void addWatchNum(Long id);

    @Update("update activity set watch_num=watch_num-1 where id=#{id}")
    void subWatchNum(Long matterId);

    @Update("update activity set book_count=book_count+1 where id=#{id}")
    void addBookCount(Long id);

    @Update("update activity set book_count=book_count-1 where id=#{id}")
    void subBookCount(Long matterId);
}
