package com.example.component_theme;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by gary on 2017/12/1.主题模式从sp中获取
 */

public class ThemeSpUtils {

    private final static String XML_NAME = "theme_settings";
    public static final String THEME_MODE = "theme_mode";
    public static final int DEFAULE_MODE = -1;

    public static void saveTheme(Context context, int themeMode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(XML_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(THEME_MODE,themeMode);
        editor.commit();
    }

    public static int getTheme(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(XML_NAME, Context.MODE_PRIVATE);
        int themeMode = sharedPreferences.getInt(THEME_MODE,DEFAULE_MODE);
        return themeMode;
    }
}
