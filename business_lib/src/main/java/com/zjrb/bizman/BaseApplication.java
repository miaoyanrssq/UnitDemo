package com.zjrb.bizman;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.component_db.DBComponent;
import com.example.component_theme.ThemeComponent;
import com.socks.library.KLog;
import com.zjrb.bizman.commonlib.R;
import com.zjrb.bizman.net_component.NetComponent;
import com.zjrb.bizman.theme.ThemeColor;
import com.zjrb.bizman.theme.ThemeMode;
import com.zjrb.bizman.utils_component.BuildConfig;

/**
 * Created by ASUS on 2017/11/14.
 */

public class BaseApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
        KLog.init(BuildConfig.IS_DEBUG_MODE);
        NetComponent.init(this);
        initThemeComponent();
        DBComponent.init(this);

    }

    private void initThemeComponent() {
        //初始化ThemeComponent
        ThemeComponent.getDefault().init(getApplicationContext());
        //初始化自定义主题
        ThemeComponent.getDefault().addThemeMode(ThemeMode.THEME_DAY,R.style.biz_DayTheme);
        ThemeComponent.getDefault().addThemeMode(ThemeMode.THEME_NIGHT,R.style.biz_NightTheme);
        ThemeComponent.getDefault().setDefaultThemeMode(ThemeMode.THEME_DAY);
        //初始化自定义主题中使用的颜色
        ThemeComponent.getDefault().addThemeColor(ThemeColor.COLOR_BACKGROUND,R.attr.biz_vg_backgroundcolor);
        ThemeComponent.getDefault().addThemeColor(ThemeColor.COLOR_TEXT,R.attr.biz_text_color);
    }


    public static Context getContext() {
        return sContext;
    }
}
