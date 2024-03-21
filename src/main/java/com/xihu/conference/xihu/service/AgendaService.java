package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.entity.Agenda;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface AgendaService {

    void insertOne(Agenda agenda);

    void deleteOneById(Long id);

    void changeStatus2Live(Long id);

    void changeStatus2playback(Long id);

    void updateOneById(Agenda agenda);

    Agenda selectById(Long id);

    List<Agenda> selectByName(String name);

    List<Agenda> selectByTag(String tag);

    List<Agenda> selectByDate(String dateString);

    List<Agenda> selectAll();

    List<String> showTags();

    /**
     * 需要将Timestamp转化格式
     * @return 显示所有的日期，如5月7日，5月8日
     */
    List<String> showDates();
}
