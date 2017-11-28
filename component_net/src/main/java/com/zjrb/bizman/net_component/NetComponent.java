package com.zjrb.bizman.net_component;

import android.content.Context;

/**
 * Created by lujialei on 2017/11/28.
 */

public class NetComponent {

    private static  Context applicationContext;

    public static void init(Context context){
        applicationContext = context;
    }

    private static void checkInit() throws Exception {
        if(applicationContext == null){
            throw new Exception("please init NetComponent before use");
        }
    }

    public static Context getContext() throws Exception {
        checkInit();
        return applicationContext;
    }
}
