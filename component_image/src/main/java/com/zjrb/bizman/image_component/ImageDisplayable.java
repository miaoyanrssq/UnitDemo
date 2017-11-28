package com.zjrb.bizman.image_component;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;

import java.io.File;

/**
 * 显示接口
 * Created by lujialei
 */
public interface ImageDisplayable {
    
    /**
     * 展示图片
     * 只设置加载失败的图片不设置展位图
     *
     * @param context
     * @param imageView
     * @param url
     * @param errorholder
     */
    void display(Context context, ImageView imageView, String url, int errorholder);
    
    
    /**
     * 展示图片
     *
     * @param context
     * @param imageView
     * @param url
     * @param placeHolder
     * @param errorholder
     */
    void display(Context context, ImageView imageView, String url, int placeHolder, int errorholder);
    
    /**
     * 展示图片
     *
     * @param context
     * @param imageView
     * @param url
     */
    void display(Context context, ImageView imageView, String url);
    
    /**
     * 裁剪圆形图片
     *
     * @param context
     * @param imageView
     * @param url
     * @param placeHolder
     * @param errorholder
     */
    void displayCircle(Context context, ImageView imageView, String url, int placeHolder, int errorholder);
    
    /**
     * 加载本地图片
     *
     * @param context
     * @param imageView
     * @param resId
     * @param placeHolder
     * @param errorholder
     */
    void diplayLocal(Context context, ImageView imageView, int resId, int placeHolder, int errorholder);
    
    /**
     * 加载本地图片
     *
     * @param context
     * @param imageView
     * @param file
     */
    void displayCircle(Context context, ImageView imageView, File file);
    
    /**
     * 加载本地图片File (ImageViewTarget耦合了)
     *
     * @param context
     * @param url
     * @param target
     */
    void displayWithTarget(Context context, String url, ImageViewTarget target, int placeHolder);
    
    /**
     * 加载本地图片File (ImageViewTarget耦合了)
     *
     * @param context
     * @param url
     * @param target
     */
    void displayWithTarget(Context context, String url, ImageViewTarget target, int placeHolder, int errorHolder);
    
    /**
     * 加载本地文件 ScaleType
     *
     * @param context
     * @param imageView
     * @param url
     * @param placeHolder
     * @param scaleType
     */
    void displayWithTarget(Context context, ImageView imageView, String url, int placeHolder, ImageView.ScaleType scaleType);
    
    /**
     * 加载本地图片 设置本地图片
     *
     * @param context
     * @param imageView
     * @param placeHolder
     * @param scaleType
     */
    void displayWithTarget(Context context, ImageView imageView, int resId, int placeHolder, ImageView.ScaleType scaleType);





}
