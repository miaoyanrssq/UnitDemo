package com.zjrb.bizman.utils_component.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断网络是否可用（七牛播放器使用）
 */
public class NetworkUtil {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    
    /**
     * 当前处于的网络
     * 0 ：null
     * 1 ：2G/3G
     * 2 ：wifi
     */
    
    
    /**
     * 获取网络模式
     *
     * @param context
     * @return
     */
    public static int getNetworkMode(Context context) {
        int mode = 0;
        
        // 获取网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        
        // 获取当前网络状态信息
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        
        if (info != null && info.isAvailable()) {
            
            //当NetworkInfo不为空且是可用的情况下，获取当前网络的Type状态
            //根据NetworkInfo.getTypeName()判断当前网络
            String name = info.getTypeName();
            
            //更改NetworkStateService的静态变量，之后只要在Activity中进行判断就好了
            if (name.equals("WIFI")) {
                mode = 2;
            } else {
                mode = 1;
            }
            
        } else {
            // NetworkInfo为空或者是不可用的情况下
            mode = 0;
        }
        
        return mode;
    }
}
