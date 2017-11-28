package com.zjrb.bizman.utils_component.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimationUtil {
    private AnimationUtil() {
    }
    
    /**
     * 根据当前的状态来旋转箭头。
     */
    @SuppressWarnings("all")
    public static void rotateArrow(View arrow, boolean flag) {
        float pivotX = arrow.getWidth() / 2f;
        float pivotY = arrow.getHeight() / 2f;
        float fromDegrees = 0f;
        float toDegrees = 0f;
        // flag为true则向上
        if (flag) {
            fromDegrees = 0f;
            toDegrees = 180f;
        } else {
            fromDegrees = 180f;
            toDegrees = 0f;
        }
        //旋转动画效果   参数值 旋转的开始角度  旋转的结束角度  pivotX x轴伸缩值
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY);
        //该方法用于设置动画的持续时间，以毫秒为单位
        animation.setDuration(350);
        //animation.setRepeatMode(Animation.RESTART);
        //设置重复次数
        //animation.setRepeatCount(3);
        //动画终止时停留在最后一帧
        animation.setFillAfter(true);
        //启动动画
        arrow.startAnimation(animation);
    }
    
    /**
     * 移动+隐藏动画
     *
     * @param curV 当前需要移动+隐藏动画的View
     */
    public static void translationXAndAlpha(final View curV, ValueAnimator.AnimatorUpdateListener listener) {
        AnimatorSet animSet = new AnimatorSet();
        ValueAnimator anim1 = ObjectAnimator.ofFloat(curV, "translationX", curV.getTranslationX(), -curV.getWidth());
        anim1.setRepeatCount(0);
        ValueAnimator anim2 = ObjectAnimator.ofFloat(curV, "alpha", 1f, 0f);
        anim2.addUpdateListener(listener);
        anim2.setRepeatCount(0);
        animSet.playTogether(anim1, anim2);
        animSet.setDuration(500);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }
            
            @Override
            public void onAnimationEnd(Animator animation) {
                curV.setVisibility(View.GONE);
            }
            
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animSet.start();
    }
    
    /**
     * 旋转动画
     */
    @SuppressWarnings("all")
    public static void rotateCircle(View arrow, boolean flag) {
        float fromDegrees = 0f;
        float toDegrees = 0f;
        // flag为true则向上
        if (flag) {
            fromDegrees = 0f;
            toDegrees = 360f;
        } else {
            fromDegrees = 360f;
            toDegrees = 0f;
        }
        //旋转动画效果   参数值 旋转的开始角度  旋转的结束角度  pivotX x轴伸缩值
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //该方法用于设置动画的持续时间，以毫秒为单位
        animation.setDuration(1000);
        animation.setRepeatCount(Animation.INFINITE);
        //animation.setRepeatMode(Animation.RESTART);
        //设置重复次数
        //animation.setRepeatCount(3);
        //动画终止时停留在最后一帧
        animation.setFillAfter(true);
        //启动动画
        arrow.startAnimation(animation);
    }
    
    public static class ViewHolder {
        private SparseArray<View> mViews;
        private View mConvertView;
        public int groupPosition;
        public int childPosition;
        
        private ViewHolder(Context context, ViewGroup parent, int layoutId, int... position) {
            this.mViews = new SparseArray<View>();
            mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            mConvertView.setTag(this);
            if (position != null) {
                if (position.length == 1) {
                    this.groupPosition = position[0];
                } else if (position.length == 2) {
                    this.groupPosition = position[0];
                    this.childPosition = position[1];
                }
            }
        }
        
        /**
         * 获取当前Holder
         */
        public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int... position) {
            if (convertView == null) {
                return new ViewHolder(context, parent, layoutId, position);
            }
            return (ViewHolder) convertView.getTag();
        }
        
        public View getConvertView() {
            return mConvertView;
        }
        
        /**
         * 通过控件的Id获取控件引用
         */
        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
        
        /**
         * 为TextView设置显示内容
         */
        public TextView setText(int viewId, String text) {
            TextView view = getView(viewId);
            view.setText(text);
            return view;
        }
        
        /**
         * 为ImageView设置显示图片
         */
        public ImageView setImageResource(int viewId, int drawableId) {
            ImageView view = getView(viewId);
            view.setImageResource(drawableId);
            return view;
        }
        
        /**
         * 为ImageView设置显示图片
         */
        public ImageView setImageBitmap(int viewId, Bitmap bmp) {
            ImageView view = getView(viewId);
            view.setImageBitmap(bmp);
            return view;
        }
               
    }
}


