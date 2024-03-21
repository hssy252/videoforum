package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Album;
import com.xihu.conference.xihu.vo.ImageAlbumVO;
import com.xihu.conference.xihu.vo.VideoAlbumVO;
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
public interface AlbumMapper {


    List<ImageAlbumVO> showImageAlbum();

    @Update("update album set count=count+1 where id=#{id}")
    void addCount(Long id);

    @Update("update album set count=count+#{num} where id=#{id}")
    void addCountWithNum(Integer num,Long id);

    @Insert("insert into album(count, like_num, type, category) values(#{count},#{likeNum},#{type},#{category}) ")
    void addAlbum(Album album);

    List<VideoAlbumVO> showVideoAlbum();

    @Select("select * from album where type=#{type} and is_delete=0")
    List<Album> showByType(Short type);
}
