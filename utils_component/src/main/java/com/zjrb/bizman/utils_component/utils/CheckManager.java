/*
 * Created by DaXiang on  2017 
 * Copyright (c) 2017. All rights reserved.
 *
 * Last modified 17-5-12 上午9:26
 */

package com.zjrb.bizman.utils_component.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lujialei
 * 处理版本更新的Manager类
 */

public class CheckManager {
    
    private Context mContext;
    private CheckManager mInstance;
    private File newApk;
    
    /**
     * @author lujl
     * @time 2017/3/23  20:20
     * @describe 检查服务器版本
     */
    public String getServerVersion() {
        return null;
    }
    
    public CheckManager(Context context) {
        super();
        mContext = context;
    }
    
    /**
     * @param url
     * @author lujl
     * @time 2017/3/24  11:07
     * @describe 下载apk
     */
    public void downloadAPK(final String url) {
        newApk = new File(Environment.getExternalStorageDirectory(), "update.apk");
        
        if (newApk.exists()) {
            newApk.delete();
        }
        
        new Thread() {
            @Override
            public void run() {
                try {
                    URL downloadUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
                    connection.setConnectTimeout(5 * 1000);
                    connection.connect();
                    int contentLength = connection.getContentLength();
                    if (contentLength > 0 && mOnDownload != null) {
                        mOnDownload.onGetContentLength(contentLength);
                    }
                    
                    InputStream inputStream = connection.getInputStream();
                    FileOutputStream fos = new FileOutputStream(newApk);
                    byte[] b = new byte[16 * 1024];
                    int len;
                    int totalLenth = 0;
                    while ((len = inputStream.read(b)) != -1) {  //先读到内存
                        fos.write(b, 0, len);
                        totalLenth += len;
                        if (mOnDownload != null) {
                            mOnDownload.onDownloading(totalLenth);
                        }
                    }
                    fos.flush();
                    
                    if (fos != null) {
                        fos.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    mOnDownload.onDownloadSuccess();
                } catch (Exception e) {
                    e.printStackTrace();
                    mOnDownload.onDownloadError();
                }
                
            }
        }.start();
        
        
    }


/*    *
     * @author lujl
     * @time 2017/3/27  11:34
     * @describe 安装apk*/
    
    public void installAPK(String authorities) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, authorities, newApk);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(newApk), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        mContext.startActivity(intent);
    }
    
    /**
     * @author lujl
     * @time 2017/3/27  11:36
     * @describe 下载成功和失败的回调
     */
    public interface OnDownload {
        
        void onGetContentLength(long contentLength);
        
        void onDownloadSuccess();
        
        void onDownloading(long progress);
        
        void onDownloadError();
    }
    
    private OnDownload mOnDownload;
    
    public void setOnDownload(OnDownload mOnDownload) {
        this.mOnDownload = mOnDownload;
    }
}
