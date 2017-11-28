package com.zjrb.bizman.utils_component.utils;

/**
 * Created by lujialei
 */

public class TimeUtils {
    public static String change(int second) {
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second % 3600;
        if (second > 3600) {
            h = second / 3600;
            if (temp != 0) {
                if (temp > 60) {
                    d = temp / 60;
                    if (temp % 60 != 0) {
                        s = temp % 60;
                    }
                } else {
                    s = temp;
                }
            }
        } else {
            d = second / 60;
            if (second % 60 != 0) {
                s = second % 60;
            }
        }
        
        return h > 0 ? h + "时" + d + "分" + s + "秒" : (d > 0 ? d + "分" + s + "秒" : s + "秒");
    }
}
