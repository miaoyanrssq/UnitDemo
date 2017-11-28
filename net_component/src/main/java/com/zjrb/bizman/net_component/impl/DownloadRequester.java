package com.zjrb.bizman.net_component.impl;

import android.os.Environment;

import com.zjrb.bizman.net_component.IDownload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lujialei
 */
public class DownloadRequester implements IDownload{
    
    private static DownloadRequester downloadRequester;
    private final OkHttpClient okHttpClient;
    
    public static DownloadRequester get() {
        if (downloadRequester == null) {
            downloadRequester = new DownloadRequester();
        }
        return downloadRequester;
    }
    
    public DownloadRequester() {
        okHttpClient = new OkHttpClient();
    }
    
    /**
     * @param url      下载连接
     * @param saveDir  储存下载文件的SDCard目录
     * @param listener 下载监听
     */
    @Override
    public void download(final String url, final File saveDir, final OnDownloadListener listener) throws IllegalArgumentException {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                listener.onDownloadFailed();
            }
    
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    if(!saveDir.exists()){
                        saveDir.mkdirs();
                    }
                    fos = new FileOutputStream(saveDir);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        listener.onDownloading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    listener.onDownloadSuccess();
                } catch (Exception e) {
                    listener.onDownloadFailed();
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }


    /**
     * @param saveDir
     * @return
     * @throws IOException 判断下载目录是否存在
     */
    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

    
    public interface OnDownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess();
        
        /**
         * @param progress 下载进度
         */
        void onDownloading(int progress);
        
        /**
         * 下载失败
         */
        void onDownloadFailed();
    }
}