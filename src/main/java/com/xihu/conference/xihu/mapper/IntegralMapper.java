package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Integral;
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
public interface IntegralMapper {

    @Insert("insert into integral(type, description, amount, detail, to_do, finished_words,url) values(#{type},#{description},#{amount},#{detail},#{toDo},#{finishedWords},#{url})")
    void insertOne(Integral integral);

    @Update("update integral set is_delete=1 where id=#{id}")
    void deleteById(Long id);

    @Select("select * from integral where id=#{id} and is_delete=0 ")
    Integral selectById(Long id);

    @Select("select * from integral where is_delete=0")
    List<Integral> selectAll();

    @Update("update integral set type=#{type},description=#{description},amount=#{amount},detail=#{detail},to_do=#{toDo},finished_words=#{finishedWords},url=#{url} where id=#{id}")
    void updateById(Integral integral);

}
