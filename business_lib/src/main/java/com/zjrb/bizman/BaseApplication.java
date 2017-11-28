package com.zjrb.bizman;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by ASUS on 2017/11/14.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
