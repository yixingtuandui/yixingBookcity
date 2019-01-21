package com.tecode.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
@Component
public class DataUtil {
    private final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 获得当前时间
     */
    public Date timeDay()  {
       Date time=null;
        try {
            time=longSdf.parse(longSdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
    /**
     * 获得本周的第一天，周一
     */
    public Date getCurrentWeekDayStartTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
            c.add(Calendar.DATE, -weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime())+" 00:00:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获得本周的最后一天，周日
     */
    public Date getCurrentWeekDayEndTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, 8 - weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime())+" 23:59:59"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("本周末"+c.getTime());
        return c.getTime();
    }
    /**
     * 获得本天的开始时间
     */
    public Date getCurrentDayStartTime() {
        Date now = new Date();
        try {
            now = shortSdf.parse(shortSdf.format(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
       // System.out.println(now);
        return now;
    }

    /**
     * 获得本天的结束时间
     */
    public Date getCurrentDayEndTime() {
        Date now = new Date();
        try {
            now = longSdf.parse(shortSdf.format(now)+" 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(now);
        return now;
    }
    /**
     * 获得本月的开始时间
     */
    public Date getCurrentMonthStartTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("本月第一天"+now);
        return now;
    }

    /**
     * 本月的结束时间
     */
    public Date getCurrentMonthEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            now = longSdf.parse(shortSdf.format(c.getTime())+" 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
       // System.out.println("本月最后一天"+now);
        return now;
    }
}
