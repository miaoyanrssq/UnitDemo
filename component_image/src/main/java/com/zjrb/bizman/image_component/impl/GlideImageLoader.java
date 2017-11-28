/*
 * Created by DaXiang on  2017 
 * Copyright (c) 2017. All rights reserved.
 *
 * Last modified 17-5-12 上午9:26
 */

package com.zjrb.bizman.image_component.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.zjrb.bizman.image_component.ImageDisplayable;
import com.zjrb.bizman.image_component.transformation.GlideCircleTransform;

import java.io.File;

/**
 * Created by lujialei
 */
public class GlideImageLoader implements ImageDisplayable {

    @Override
    public void display(Context context, ImageView imageView, String url, int errorholder) {
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .error(errorholder)
                .into(new ImageViewTarget(imageView)
                );
    }

    @Override
    public void display(Context context, ImageView imageView, String url, int placeholder, int errorholder) {
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .placeholder(placeholder)
                .error(errorholder)
                .into(new ImageViewTarget(imageView)
                );
    }

    @Override
    public void display(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(new ImageViewTarget(imageView)
                );
    }


    @Override
    public void displayCircle(Context context, ImageView imageView, String url, int placeholder, int errorholder) {
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .placeholder(placeholder)
                .error(errorholder)
                .transform(new GlideCircleTransform(context))
                .into(new ImageViewTarget(imageView)
                );
    }

    @Override
    public void diplayLocal(Context context, ImageView imageView, int resId, int placeholder, int errorholder) {
        Glide.with(context).load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .placeholder(placeholder)
                .error(errorholder)
                .transform(new GlideCircleTransform(context))
                .into(new ImageViewTarget(imageView)
                );
    }

    @Override
    public void displayCircle(Context context, ImageView imageView, File file) {
        Glide.with(context).load(file)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .transform(new GlideCircleTransform(context))
                .into(new ImageViewTarget(imageView)
                );
    }

    @Override
    public void displayWithTarget(Context context, String url, com.bumptech.glide.request.target.ImageViewTarget target, int placeholder) {
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate()
                .placeholder(placeholder)
                .into(target);
    }

    @Override
    public void displayWithTarget(Context context, String url, com.bumptech.glide.request.target.ImageViewTarget target, int placeHolder, int errorHolder) {
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate()
                .placeholder(placeHolder)
                .error(errorHolder)
                .into(target);
    }

    @Override
    public void displayWithTarget(Context context, ImageView imageView, String url, int placeholder, ImageView.ScaleType scaleType) {
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(new DefaultImageViewTarget(imageView, "", scaleType)
                );
    }

    @Override
    public void displayWithTarget(Context context, ImageView imageView,int resId, int placeHolder, ImageView.ScaleType scaleType) {
        Glide.with(context).load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(new DefaultImageViewTarget(imageView, "", scaleType)
                );
    }

    private class DefaultImageViewTarget extends GlideDrawableImageViewTarget {
        private ImageView.ScaleType scaleType;

        DefaultImageViewTarget(ImageView view, String tag, ImageView.ScaleType scaleType) {
            super(view);
            this.scaleType = scaleType;
        }

        @Override
        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
            view.setScaleType(scaleType);
            super.onResourceReady(resource, animation);
        }

        @Override
        public void onLoadStarted(Drawable placeholder) {
            super.onLoadStarted(placeholder);
            view.setScaleType(ImageView.ScaleType.CENTER);
            view.setImageDrawable(placeholder);
        }
    }

    /**
     * 加载回调
     */
    public class ImageViewTarget extends GlideDrawableImageViewTarget {
        private ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_CENTER;

        public ImageViewTarget(ImageView view) {
            super(view);
        }

        public ImageViewTarget(ImageView view, String tag, ImageView.ScaleType scaleType) {
            super(view);
            this.scaleType = scaleType;
        }

        @Override
        protected void setResource(GlideDrawable resource) {
            super.setResource(resource);
        }

        @Override
        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
            view.setScaleType(scaleType);
            super.onResourceReady(resource, animation);
        }

        @Override
        public void onLoadStarted(Drawable placeholder) {
            view.setImageDrawable(placeholder);
        }

        @Override
        public void onLoadFailed(Exception e, Drawable errorDrawable) {
            if (e != null) {
                Log.d("onLoadFailed", e.getMessage());
            }
            view.setImageDrawable(errorDrawable);
        }
    }
}
