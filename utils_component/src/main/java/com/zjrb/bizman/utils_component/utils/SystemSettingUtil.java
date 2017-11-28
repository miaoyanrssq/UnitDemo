package com.zjrb.bizman.utils_component.utils;

import android.content.ContentResolver;
import android.provider.Settings;

/**
 * Created by lujialei
 */
public class SystemSettingUtil {
    
    public static final int ORITATION_LOCKED = 0;
    public static final int ORITATION_UNLOCK = 1;
    
    public static void setScreenRotation(ContentResolver cr, int rotation) {
        Settings.System.putInt(cr, Settings.System.ACCELEROMETER_ROTATION,
                rotation);
    }
    
    public static int getScreenRotation(ContentResolver cr) {
        int flag = 0;
        try {
            flag = Settings.System.getInt(cr,
                    Settings.System.ACCELEROMETER_ROTATION);
        } catch (Settings.SettingNotFoundException e) {
            flag = 0;
        }
        return flag;
    }
}
