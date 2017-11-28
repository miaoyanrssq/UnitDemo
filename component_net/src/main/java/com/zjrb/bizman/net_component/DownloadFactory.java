package com.zjrb.bizman.net_component;


import com.zjrb.bizman.net_component.impl.DownloadRequester;
import com.zjrb.bizman.net_component.interfaces.IDownload;

/**
 * Created by lujialei
 */
public class DownloadFactory {
    
    private static IDownload sInstance;
    
    public static final IDownload getInstance() {
        if (sInstance == null) {
            synchronized (DownloadFactory.class) {
                if (sInstance == null) {
                    sInstance = new DownloadRequester();
                }
            }
        }
        return sInstance;
    }
}
