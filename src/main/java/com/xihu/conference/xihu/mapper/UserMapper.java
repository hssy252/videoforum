package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Mapper
public interface UserMapper {


    @Insert("insert into user(name,password) values (#{name},#{password})")
    void insertOne(User user);

    @Delete("delete from user where id=#{id}")
    void deleteOne(Long id);

    @Delete("delete from user where id=#{id}")
    void deleteByName(String name);

    @Update("update user set name=#{name}, enterprise=#{enterprise}, department=#{department}, position=#{position}, tel=#{tel}, email=#{email} "
        + " where id = #{id}")
    void updateOne(User user);

    @Update("update user set integral=integral-#{integral} where id =#{id}")
    void consumeSome(Integer integral,Long id);

    @Select("select * from user where id=#{id} and is_delete=0")
    User selectById(Long id);

    @Select("select * from user where name=#{name} and is_delete=0 ")
    User selectByName(String name);

    @Select("select integral from user where id=#{id}")
    Integer getIntegral(Long id);

    @Update("update user set integral=integral+100 where id=#{id} and is_delete=0")
    void sign(Long id);

    @Select("select * from user where name=#{name} and password=#{password}")
    User selectWhenLogin(User user);

    @Update("update user set image=#{imageUrl} where id=#{id}")
    void updateImage(String imageUrl, Long id);

    @Select("select * from user where openid=#{openid}")
    User getByOpenid(String openid);


    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into user(openid) values (#{openid})")
    void insertWithOpenId(User user);

}
