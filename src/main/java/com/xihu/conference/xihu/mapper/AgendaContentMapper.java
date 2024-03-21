package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.AgendaContent;
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
public interface AgendaContentMapper {

    @Insert("insert into agenda_content(agenda_id, start_time, end_time, timespan, content, guest_list) values(#{agendaId},#{startTime},#{endTime},#{timespan},#{content},#{guestList})")
    void insertOne(AgendaContent agendaContent);

    @Update("update agenda_content set is_delete=1  where id=#{id}")
    void deleteById(Long id);

    @Update("update agenda_content set agenda_id=#{agendaId},start_time=#{startTime},end_time=#{endTime},timespan=#{timespan},content=#{content},guest_list=#{guestList} where id=#{id}")
    void updateOne(AgendaContent agendaContent);

    @Select("select * from agenda_content where is_delete=0 and agenda_id=#{agendaId}")
    List<AgendaContent> selectAllById(Long agendaId);
}
