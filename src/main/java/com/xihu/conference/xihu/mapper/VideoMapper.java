package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Video;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Mapper
public interface VideoMapper {

    @Insert("insert into video(name, column_type, type, cover, path, album_id, resource_id) values(#{name},#{columnType},#{type},#{cover},#{path},#{albumId},#{resourceId}) ")
    void addVideo(Video video);

    @Select("select * from video where is_delete=0 and type=1")
    List<Video> showVideo();
}
