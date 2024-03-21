package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Achievement;
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
public interface AchievementMapper {

    @Insert("insert into achievement(title, description, type, images, url,category,count) values(#{title},#{description},#{type},#{images},#{url},#{category},#{count})")
    void insertOne(Achievement achievement);

    @Update("update achievement set is_delete=1  where id=#{id}")
    void deleteById(Long id);

    @Update("update achievement set title=#{title},description=#{description},type=#{type},images=#{images},url=#{url},category=#{category},count=#{count} where id = #{id}")
    void updateOne(Achievement achievement);

    @Select("select * from achievement where type=1 and is_delete=0")
    List<Achievement> showDownloadable();

    @Select("select * from achievement where type=2 and is_delete=0")
    List<Achievement> showUsable();

    @Select("select * from achievement where is_delete=0 and category = #{category}")
    List<Achievement> showByCategory(String category);

    @Update("update achievement set count=count+#{watchNum} where id=#{id}")
    void addWatchCount(Long id, Long watchNum);
}
