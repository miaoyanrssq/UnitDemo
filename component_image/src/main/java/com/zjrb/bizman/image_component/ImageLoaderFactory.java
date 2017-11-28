package com.zjrb.bizman.image_component;


import com.zjrb.bizman.image_component.impl.GlideImageLoader;

/**
 * 图片加载工厂
 * Created by lujialei
 */
public class ImageLoaderFactory {

    private static ImageDisplayable mInstance;

    public static ImageDisplayable getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoaderFactory.class) {
                if (mInstance == null) {
                    mInstance = new GlideImageLoader();
                }
            }
        }
        return mInstance;
    }
}
