package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.IntegralRecord;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
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
public interface IntegralRecordMapper {

    @Insert("insert into integral_record(user_id, type, matter_id,change_num) values (#{userId},#{type},#{matterId},#{changeNum})")
    void insertOne(IntegralRecord integralRecord);

    @Update("update integral_record set is_delete=1 where id=#{id}")
    void deleteById(Long id);

    @Update("update integral_record set user_id=#{userId},type=#{type},matter_id=#{matterId},change_num=#{changeNum} where id=#{id}")
    void updateById(IntegralRecord integralRecord);

    @Select("select * from integral_record where id=#{id} and is_delete=0")
    IntegralRecord selectById(Long id);

    @Select("select * from integral_record where user_id=#{userId} and is_delete=0")
    List<IntegralRecord> selectAll(Long userId);

    @Select("select * from integral_record where is_delete=0")
    List<IntegralRecord> showAll();
}
