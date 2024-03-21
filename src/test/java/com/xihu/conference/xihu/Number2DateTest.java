package com.xihu.conference.xihu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public class Number2DateTest {

    @Test
    public void test01() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "5月7日";
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(date);
        int[] dates = new int[2];
        for (int i = 0; matcher.find(); i++) {
            dates[i] = Integer.parseInt(matcher.group().split("")[0]);
            System.out.println(dates[i]);
        }
        int year = new Date().getYear();

        Date finalDate = new Date(year, dates[0] - 1, dates[1]);
        calendar.setTime(finalDate);
        calendar.add(Calendar.DAY_OF_YEAR,1);
        Date endDate = calendar.getTime();
        System.out.println(simpleDateFormat.format(finalDate));
        System.out.println(simpleDateFormat.format(endDate));

    }

}
