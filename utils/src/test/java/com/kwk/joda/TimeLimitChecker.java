package com.kwk.joda;

import ognl.Ognl;
import ognl.OgnlException;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class TimeLimitChecker {
    /**
     * 表达式样例：
     * now.dateBetween("2014-06-09", "2014-8-10")
     * &&((dayOfWeek == 1 && now.timeBetween("10:30", "11:30"))
     * || (dayOfWeek == 2 && now.timeBetween("16:30", "17:30"))
     * || (dayOfWeek == 7 && now.timeBetween("12:30", "23:59")))
     * 表达式中可用的变量year,month,day,dayOfWeek,hour,minute,now，
     * 为了简化书写，now可以调用dateBetween和timeBetween方法
     * @param calendar 要判断的时间
     * @param expr 时间判断表达式
     * @return 表达的执行值
     * @throws OgnlException
     */

    public static boolean isValid(Calendar calendar, String expr) throws OgnlException {
        DateTime nowTime = new DateTime(calendar);
        CalendarAdaptor now = new CalendarAdaptor(nowTime);


        int year = nowTime.getYear();
        int month = nowTime.getMonthOfYear();
        int day = nowTime.getDayOfMonth();
        int dayOfWeek = nowTime.getDayOfWeek();
        int hour = nowTime.getHourOfDay();
        int minute = nowTime.getMinuteOfHour();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("year", year);
        map.put("month", month);
        map.put("day", day);
        map.put("dayOfWeek", dayOfWeek);
        map.put("hour", hour);
        map.put("minute", minute);
        map.put("now", now);

        Boolean ret = (Boolean)Ognl.getValue(expr, map);
        return ret;
    }
}
