package com.zjrb.bizman.utils_component.log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogcatLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.socks.library.KLog;
import com.zjrb.bizman.utils_component.BuildConfig;

/**
 * 全局日志类
 * Created by lujialei
 */
public class LogUtils {

    
    public static void i(String tag, String msg) {
        KLog.i(tag,msg);
    }
    
    public static void d(String tag, String msg) {
        KLog.w(tag,msg);
    }
    
    public static void v(String tag, String msg) {
        KLog.v(tag,msg);
    }
    
    public static void e(String tag, String msg) {
        KLog.e(tag,msg);
    }
    
    public static void w(String tag, String msg) {
        KLog.w(tag,msg);
    }
    
    public static void json(String tag,String msg) {
        KLog.json(tag,msg);
    }
    
    public static void xml(String tag, String msg) {
        KLog.xml(tag,msg);
    }
}
