package com.zjrb.bizman.net_component;


import com.zjrb.bizman.net_component.impl.OkHttpRequester;

/**
 * Created by lujialei
 */
public class ApiRequesterFactory {
    
    private static IApiReuqester sInstance;
    
    public static final IApiReuqester getInstance() {
        if (sInstance == null) {
            synchronized (ApiRequesterFactory.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpRequester();
                }
            }
        }
        return sInstance;
    }
}
