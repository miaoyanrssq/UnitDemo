package com.zjrb.bizman.utils_component.utils;

import android.view.View;

import java.lang.ref.WeakReference;
import java.util.Date;

/**
 * 全局重复点击检测工具
 *
 * @author lujialei
 * @date 2017/6/2 09:02
 */
public class ClickChecker {
    
    private static WeakReference<View> sClickView = null;
    
    private static long sLastClickTime = 0;
    
    private static final long MAX_INTERVAL_TIME = 500;
    
    /**
     * 检查是否为重复点击
     *
     * @return false 为非重复点击，true为重复点击
     */
    public static boolean check(View view) {
        if (sClickView == null) {
            sClickView = new WeakReference<View>(view);
            sLastClickTime = new Date().getTime();
            return false;
        }
        
        View clickView = sClickView.get();
        if (clickView == null) {
            sClickView = new WeakReference<View>(view);
            sLastClickTime = new Date().getTime();
            return false;
        }
        
        // 多个按钮同时点击也要过滤掉
//        if (clickView.getId() != view.getId()) {
//            sClickView = new WeakReference<View>(view);
//            sLastClickTime = new Date().getTime();
//            return false;
//        }
        
        long currentTime = new Date().getTime();
        if (currentTime - sLastClickTime > MAX_INTERVAL_TIME) {
            sLastClickTime = currentTime;
            sClickView = new WeakReference<View>(view);
            
            return false;
        }
        sLastClickTime = currentTime;
        sClickView = new WeakReference<View>(view);
        return true;
    }
}
