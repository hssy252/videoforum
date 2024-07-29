package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.dto.VideoDTO;
import com.xihu.conference.xihu.entity.Video;
import com.xihu.conference.xihu.vo.VideoVO;
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

    @Insert("insert into video(name, column_type, type, cover, path, album_id) values(#{name},#{columnType},#{type},#{cover},#{path},#{albumId}) ")
    void addVideo(VideoDTO video);

    @Select("select * from video where is_delete=0 and type=1")
    List<Video> showVideo();

    @Select("select id,name,column_type,type,cover,path,album_id,watch_count,like_num,resource_id from video where is_delete=0 and column_type=#{column}")
    List<VideoVO> showByColumn(String column);

    @Select("select distinct column_type from video where is_delete=0")
    List<String> showColumns();
}
