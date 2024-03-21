package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Goods;
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
public interface GoodsMapper {

    @Insert("insert into goods(name, price, description, remain_num,category) values (#{name},#{price},#{description},#{remainNum},#{category})")
    void insertOne(Goods goods);

    @Update("update goods set is_delete=1 where id=#{id}")
    void deleteById(Long id);

    @Update("update goods set name=#{name},price=#{price},description=#{description},remain_num=#{remainNum},category=#{category} where id =#{id}")
    void updateById(Goods goods);

    @Update("update goods set remain_num=remain_num-#{some} where id=#{id}")
    void consumeSome(Integer some, Long id);

    @Select("select * from goods where id=#{id} and is_delete=0")
    Goods selectById(Long id);

    @Select("select * from goods where category=#{tag} and is_delete=0")
    List<Goods> selectByTag(String tag);

    @Select("select * from goods where is_delete=0")
    List<Goods> selectAll();

}
