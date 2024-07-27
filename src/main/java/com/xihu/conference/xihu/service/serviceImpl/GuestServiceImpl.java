package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.Guest;
import com.xihu.conference.xihu.mapper.GuestMapper;
import com.xihu.conference.xihu.service.GuestService;
import com.xihu.conference.xihu.vo.ExpertVO;
import com.xihu.conference.xihu.vo.GuestVO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestMapper guestMapper;

    @Override
    public void addExpert(Guest guest) {
        guestMapper.addExpert(guest);
    }

    @Override
    public List<GuestVO> showAll() {
        return guestMapper.showAll();
    }

    @Override
    public List<ExpertVO> showExperts() {
        return guestMapper.showExperts();
    }

    @Override
    public List<String> showDates() {
        return guestMapper.showDates();
    }

    @Override
    public List<GuestVO> showPrevious() {
        return guestMapper.showPrevious();
    }

    @Override
    public List<GuestVO> showByDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(date);
        LinkedList<String> dateStrings = new LinkedList<>();
        while (matcher.find()){
            dateStrings.add(matcher.group(0));
        }
        try {
            Date parsed = format.parse("2024-" +dateStrings.removeFirst()  + "-" + dateStrings.removeFirst());
            //如果不用sqlDate类型则无法匹配上数据库的date类型，util包的date会在数据库转换成时间戳
            java.sql.Date agendaDate = new java.sql.Date(parsed.getTime());
            return guestMapper.showByDate(agendaDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
