package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.Agenda;
import com.xihu.conference.xihu.mapper.AgendaMapper;
import com.xihu.conference.xihu.service.AgendaService;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Service
public class AgendaServiceImpl implements AgendaService {

    @Autowired
    private AgendaMapper agendaMapper;

    @Override
    public void insertOne(Agenda agenda) {
        agendaMapper.insertOne(agenda);
    }


    @Override
    public void deleteOneById(Long id) {
        agendaMapper.deleteById(id);
    }

    @Override
    public void changeStatus2Live(Long id) {
        agendaMapper.changeStatus2Live(id);
    }

    @Override
    public void changeStatus2playback(Long id) {
        agendaMapper.changeStatus2playback(id);
    }


    @Override
    public void updateOneById(Agenda agenda) {
        //未完成名字检查
        agendaMapper.updateOneById(agenda);
    }

    @Override
    public Agenda selectById(Long id) {
        return agendaMapper.selectById(id);
    }

    @Override
    public List<Agenda> selectByName(String name) {
        return agendaMapper.selectByName(name);
    }

    @Override
    public List<Agenda> selectByTag(String tag) {
        return agendaMapper.selectByTag(tag);
    }

    @Override
    public List<Agenda> selectByDate(String dateString) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(dateString);
        int[] dates = new int[2];
        //筛选出5月7日中的数字
        for (int i=0; matcher.find();i++){
            dates[i]=Integer.parseInt(matcher.group(0));
            System.out.println(dates[i]);
        }
        int year = LocalDateTime.now().getYear();
        //将localDate转换为Date
        LocalDate localDate = LocalDate.of(year,dates[0],dates[1]);
//        System.out.println(dates[1]);
        Instant instant = LocalDateTime.of(localDate, LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant();
        Date beginDate = Date.from(instant);
        calendar.setTime(beginDate);
        calendar.add(Calendar.DAY_OF_YEAR,1);
        Date endDate = calendar.getTime();
        String beginTime = simpleDateFormat.format(beginDate);
        String endTime = simpleDateFormat.format(endDate);
        return agendaMapper.selectByDate(beginTime,endTime);
    }

    @Override
    public List<Agenda> selectAll() {
        return agendaMapper.selectAll();
    }

    @Override
    public List<String> showTags() {
        return agendaMapper.showTags();
    }

    @Override
    public List<String> showDates() {
        List<Timestamp> timestamps = agendaMapper.showDates();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        List<String> dates = new ArrayList<>();
        String date = "";
        String[] split = null;
        for (Timestamp timestamp : timestamps) {
            split = format.format(timestamp).split("-");
            date = split[0] + "月" + split[1] + "日";
            dates.add(date);
        }
        List<String> newList = dates.stream().distinct().collect(Collectors.toList());
        dates.clear();
        dates.addAll(newList);
        return dates;
    }
}
