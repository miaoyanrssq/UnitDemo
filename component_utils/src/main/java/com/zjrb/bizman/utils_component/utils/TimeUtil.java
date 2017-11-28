package com.zjrb.bizman.utils_component.utils;

import java.text.SimpleDateFormat;

/**
 * Created by lujialei
 */
public class TimeUtil {
    
    /**
     * 转换成天时分秒
     *
     * @param ms
     * @return
     */
    public static String formatTime(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;
        long day = ms / dd;
        long hour = (ms - (day * dd)) / hh;
        long minute = (ms - (day * dd) - (hour * hh)) / mi;
        long second = (ms - (day * dd) - (hour * hh) - (minute * mi)) / ss;
        long milliSecond = ms - (day * dd) - (hour * hh) - (minute * mi) - (second * ss);
        String strDay = String.valueOf(day); //天
        String strHour = String.valueOf(hour);//小时
        String strMinute = String.valueOf(minute);//分钟
        String strSecond = String.valueOf(second);//秒
        if(day == 0){
            if (hour == 0) {
                if (minute == 0) {
                    return strSecond + "秒";
                } else {
                    return strMinute + "分钟" + strSecond + "秒";
                }
            } else {
                return strHour + "小时" + strMinute + "分钟" + strSecond + "秒";
            }
        }else{
            return strDay+"天"+strHour + "小时" + strMinute + "分钟" + strSecond + "秒";
        }
    }
    
    /**
     * Unix时间戳转换
     *
     * @param format "yyyy-MM-dd HH:mm:ss"
     * @param time   毫秒
     * @return
     */
    public static String formatTime(String format, String time) {
        try {
            Long timestamp = Long.parseLong(time);
            SimpleDateFormat f = new SimpleDateFormat(format);
            return f.format(new java.util.Date(timestamp));
        } catch (Exception e) {
            return String.valueOf("1970-01-01 00:00:00");
        }
    }
}
