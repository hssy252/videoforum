package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Guest;
import com.xihu.conference.xihu.vo.ExpertVO;
import com.xihu.conference.xihu.vo.GuestVO;
import java.sql.Date;
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
public interface GuestMapper {

    @Insert("insert into guest(name, description, image, agenda_id,is_expert) VALUES(#{name},#{description},#{image},#{agendaId},#{isExpert})")
    void addExpert(Guest guest);


    List<GuestVO> showAll();

    @Select("select id,name,description,image from guest where is_expert=1 and is_delete=0")
    List<ExpertVO> showExperts();

    @Select("select distinct DATE_FORMAT(agenda_time,'%m月%d日') from guest where agenda_time is not null and is_previous=0 and is_delete=0")
    List<String> showDates();


    List<GuestVO> showPrevious();

    List<GuestVO> showByDate(Date parsed);
}
