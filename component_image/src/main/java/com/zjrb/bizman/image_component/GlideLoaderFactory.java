package com.zjrb.bizman.image_component;

import android.content.Context;
import android.os.Looper;

import com.bumptech.glide.Glide;

/**
 * Author: Z.T on 17/9/19.
 * Describe:Glide 图片工厂 统一入口
 */
public class GlideLoaderFactory {

    private static GlideLoaderFactory mInstance;

    public static GlideLoaderFactory getInstance() {
        if (mInstance == null) {
            synchronized (GlideLoaderFactory.class) {
                if (mInstance == null) {
                    mInstance = new GlideLoaderFactory();
                }
            }
        }
        return mInstance;
    }


    // 清除图片磁盘缓存 子线程执行
    public boolean clearDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 清除Glide内存缓存 只能在主线程执行
    public boolean clearMemoryCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Glide.get(context).clearMemory();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
