package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.News;
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
public interface NewsMapper {

    @Insert("insert into news(title, publisher, image, description, publish_time) VALUES(#{title},#{publisher},#{image},#{description},#{publishTime})")
    void addNews(News news);

    @Select("select * from news where is_delete=0")
    List<News> showAll();
}
