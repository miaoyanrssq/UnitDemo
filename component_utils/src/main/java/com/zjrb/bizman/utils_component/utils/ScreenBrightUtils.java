package com.zjrb.bizman.utils_component.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.provider.Settings;
import android.view.WindowManager;

/**
 * Created bylujialei
 */

public class ScreenBrightUtils {
    
    /**
     * 是否开启了自动调节亮度
     */
    public static boolean isAutoBrightness(ContentResolver aContentResolver) {
        boolean automicBrightness = false;
        try{
            automicBrightness = Settings.System.getInt(aContentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch(Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return automicBrightness;
    }
    
    /**
     * 获取屏幕亮度
     */
    public static int getScreenBrightness(Activity activity) {
        int nowBrightnessValue = 0;
        ContentResolver resolver = activity.getContentResolver();
        try{
            nowBrightnessValue = Settings.System.getInt(resolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return nowBrightnessValue;
    }
    
    /**
     * 设置屏幕亮度
     */
    public static void setBrightness(Activity activity, int brightness) {
        // Settings.System.putInt(activity.getContentResolver(),
        // Settings.System.SCREEN_BRIGHTNESS_MODE,
        // Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);
        activity.getWindow().setAttributes(lp);
    }
    
    /**
     * 关闭自动亮度调节
     */
    public static void stopAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,  Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
    }
    
    /**
     * 开启自动亮度调节
     */
    public static void startAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    }
    
}
