package com.zjrb.bizman;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.component_db.DBComponent;
import com.socks.library.KLog;
import com.zjrb.bizman.net_component.NetComponent;
import com.zjrb.bizman.utils_component.BuildConfig;

import solid.ren.skinlibrary.SkinConfig;
import solid.ren.skinlibrary.base.SkinBaseApplication;

/**
 * Created by ASUS on 2017/11/14.
 */

public class BaseApplication extends SkinBaseApplication {

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
        DBComponent.init(this);
        SkinConfig.setCanChangeStatusColor(true);
        SkinConfig.setCanChangeFont(true);
        SkinConfig.setDebug(true);
        SkinConfig.enableGlobalSkinApply();

    }



    public static Context getContext() {
        return sContext;
    }
}
