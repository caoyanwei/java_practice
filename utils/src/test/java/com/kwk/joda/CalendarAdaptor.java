package com.kwk.joda;


import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CalendarAdaptor {
    private static final DateTimeFormatter dateFmt = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeFmt = DateTimeFormat.forPattern("HH:mm");

    private DateTime now;

    public CalendarAdaptor(DateTime now) {
        this.now = now;
    }

    /**
     * 判断calender是否在指定的日期范围内，如果时间表达格式不对将抛出异常
     *
     * @param begin 起始日期，格式样例：2014-2-28 或2014-02-28
     * @param end   结束日期
     * @return 是否在上述范围内
     */
    public boolean dateBetween(String begin, String end) {
        DateTime beginDateTime = DateTime.parse(begin, dateFmt);
        DateTime endDateTime = DateTime.parse(end, dateFmt);
        endDateTime = endDateTime.plusDays(1);
        boolean ret = now.isAfter(beginDateTime) && now.isBefore(endDateTime);
        return ret;
    }

    /**
     * 判断calender是否在指定的时间范围内，如果时间表达格式不对将抛出异常
     *
     * @param begin 起始时间，格式样例：10:00-21:00
     * @param end   结束时间
     * @return 是否在上述范围内
     */
    public boolean timeBetween(String begin, String end) {
        if ("24:00".equals(end)) {
            end = "23:59";
        }

        MutableDateTime beginDateTime = MutableDateTime.parse(begin, timeFmt);
        beginDateTime.setDate(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth());

        MutableDateTime endDateTime = MutableDateTime.parse(end, timeFmt);
        endDateTime.setDate(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth());
        endDateTime.addMinutes(1);

        boolean ret = now.isAfter(beginDateTime) && now.isBefore(endDateTime);
        return ret;
    }
}

