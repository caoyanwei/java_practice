package com.kwk.joda;


import ognl.OgnlException;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Calendar;

public class JodaTest {
    //@Test
    public void basicTest() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");

        DateTime dateTime = DateTime.parse("2014-02-19", fmt);
        System.out.println(dateTime);

        DateTime now = DateTime.now();
        DateTimeFormatter fmt2 = DateTimeFormat.forPattern("HH:mm");
        MutableDateTime beginDateTime = MutableDateTime.parse("12:30", fmt2);
        beginDateTime.setDate(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth());
        System.out.println(beginDateTime);
    }

    //@Test
    public void calenderAdaptorTest() {
        CalendarAdaptor cal = new CalendarAdaptor(DateTime.now());
        boolean ret = cal.dateBetween("2014-06-09", "2014-06-10");
        System.out.println(ret);

        boolean ret2 = cal.timeBetween("16:50", "16:56");
        System.out.println(ret2);
    }

    @Test
    public void timeLimitCheckerTest() throws OgnlException {
        String expr = "dayOfWeek == 2 && now.timeBetween(\"12:30\", \"17:30\") && now.dateBetween(\"2014-06-09\", \"2014-8-10\")";
        boolean ret = TimeLimitChecker.isValid(Calendar.getInstance(), expr);
        System.out.println(ret);
    }
}
