package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Image;
import com.xihu.conference.xihu.vo.ImageVO;
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
public interface ImageMapper {

    @Insert("insert into image(name, column_type, type, url, album_id) VALUES(#{name},#{columnType},#{type},#{url},#{albumId}) ")
    void addImage(Image image);

    @Select("select * from image where type=#{type} and is_delete=0")
    List<Image> selectByType(Short type);

    @Select("select distinct column_type from image where is_delete=0")
    List<String> showColumns();

    @Select("select id,name,column_type,type,url,album_id from image where column_type=#{column} and type=3 and is_delete=0")
    List<ImageVO> showByColumn(String column);
}
