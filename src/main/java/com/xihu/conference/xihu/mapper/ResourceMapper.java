package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.vo.ResourceVO;
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
public interface ResourceMapper {

    @Select("select id,name,cover,description,url,type from resource where is_delete=0 and type=#{type}")
    List<ResourceVO> listResources(Short type);

    @Insert("insert into resource(name,url,cover,description,type) values(#{name},#{url},#{cover},#{description},#{type})")
    void upload(String name, String url, String cover, String description,Short type);

    @Select("select url from resource where id=#{id} and is_delete=0")
    String fetchUrl(Long id);
}
