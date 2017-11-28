package com.zjrb.bizman.utils_component.utils;



import com.zjrb.bizman.utils_component.log.LogUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static final String LOG_TAG = DateUtils.class.getSimpleName();
    private static SimpleDateFormat sFullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final int ONE_HOUR = 60 * 60;
    private static final int ONE_MINITE = 60;
    private static DecimalFormat sNumberFormat = new DecimalFormat("00");
    
    public static String GeneralFormatDate(String fullDateTime) {
        try {
            SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dfYear = new SimpleDateFormat("yyyy");
            
            SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm:ss");                // 当天显示时间
            SimpleDateFormat dfOutYear = new SimpleDateFormat("MM-dd HH:mm:ss");       // 当年显示月份时间
            SimpleDateFormat dfFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 往年显示完全
            
            Date dateFull = dfFull.parse(fullDateTime);
            Date dateDate = dfDate.parse(fullDateTime);
            Date dateNow = new Date();
            
            String now = dfDate.format(dateNow);
            String date = dfDate.format(dateDate);
            
            // 同一年
            String yearNow = dfYear.format(dateNow);
            String yearChange = dfYear.format(dateDate);
            
            if (now.equals(date)) {
                return dfTime.format(dateFull);
            } else if (yearNow.equals(yearChange)) {
                return dfOutYear.format(dateFull);
            } else {
                return dfFull.format(dateFull);
            }
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
            return new String();
        }
    }
    
    public static String getDateTimeStr(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(time));
    }
    
    public static long parseTimeStr(String timeStr) {
        try {
            return sFullDateFormat.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    /**
     * @param time
     * @return 返回时间格式为"yyyy-MM-dd HH:mm:ss"
     */
    public static String getFullDateTimeStr(long time) {
        return sFullDateFormat.format(new Date(time));
    }
    
    public static String getVideoPlayTime(int time) {
        String timeStr = "";
        time = time / 1000;
        if (time >= ONE_HOUR) {
            timeStr += sNumberFormat.format(time / ONE_HOUR) + ":";
            time = time % ONE_HOUR;
        }
        if (time >= ONE_MINITE) {
            timeStr += sNumberFormat.format(time / ONE_MINITE) + ":";
            time = time % ONE_MINITE;
        } else {
            timeStr += "00:";
        }
        timeStr += sNumberFormat.format(time);
        return timeStr;
    }


    /**
     * 返回时间格式为"mm分ss秒"
     */
    public static String formartTimeStr(long timeValue, String formart) {
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(timeValue);
        SimpleDateFormat fmat=new SimpleDateFormat("mm分ss秒");
        String time=fmat.format(calendar.getTime());
        return time;
    }
}
