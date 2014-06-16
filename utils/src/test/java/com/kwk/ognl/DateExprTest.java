package com.kwk.ognl;


import ognl.Ognl;
import ognl.OgnlException;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DateExprTest {

    @Test
    public void basicTest() throws OgnlException {
        Calendar now = Calendar.getInstance();

//        System.out.println(now.get(Calendar.YEAR));
//        System.out.println(now.get(Calendar.MONTH) + 1);
//        System.out.println(now.get(Calendar.DAY_OF_MONTH));
//        System.out.println(now.get(Calendar.DAY_OF_WEEK) - 1);
//        System.out.println(now.get(Calendar.HOUR_OF_DAY));
//        System.out.println(now.get(Calendar.MINUTE));


        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = now.get(Calendar.DAY_OF_WEEK) - 1;
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("year", year);
        map.put("month", month);
        map.put("day", day);
        map.put("dayOfWeek", dayOfWeek);
        map.put("hour", hour);
        map.put("minute", minute);
        map.put("now", now);



//        boolean ret = (year == 2014) //2014年
//                && ((month == 6 && day >= 1) || (month == 7) || (month == 8 && day <= 10))//6月20到8月10
//                && (dayOfWeek == 2 && ((hour >= 10 && hour <= 11) || (hour >= 16 && hour <= 17))) //周二10点到11点或者16点到17点
//                && (dayOfWeek == 4 && (hour >= 16 && hour <= 17));//周四16点到17点

        String expr = "(year == 2014) " + //2014年
                "&& ((month == 6 && day >= 10) || (month == 7) || (month == 8 && day <= 10))" + //6月20到8月10
                "&& ((dayOfWeek == 2 && ((hour >= 10 && hour <= 12) || (hour >= (12 + 1) && hour <= 17))) " + //周二10点到11点或者16点到17点
                "|| (dayOfWeek == 4 && (hour >= 16 && hour <= 17)))";//周四16点到17点


        //String expr3 = "#now.get(Calendar.YEAR) + #now.get(Calendar.MONTH)";
        //String expr3 = "now.get(@java.util.Calendar@YEAR) * now.get(@java.util.Calendar@MONTH)";
        for (int i = 0; i < 100000; i++) {
            Object obj = Ognl.getValue(expr, map);
        }

        //System.out.println(obj);
    }
}
