package com.zjrb.bizman.unitdemo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.socks.library.KLog;
import com.zjrb.bizman.utils_component.*;
import com.zjrb.bizman.utils_component.BuildConfig;
import com.zjrb.bizman.utils_component.log.LogUtils;

/**
 * Created by lujialei on 2017/11/23.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
        KLog.init(BuildConfig.IS_DEBUG_MODE);
    }
}
