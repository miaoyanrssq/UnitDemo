package com.zjrb.bizman.utils_component.utils;

import android.app.Activity;
import android.view.View;

/**
 * 软键盘监视器
 * Created by lujialei
 */
public class KeyboardListenerUtil implements View.OnLayoutChangeListener {
    
    private int screenHeight = 0;
    private int keyHeight = 0;
    private View mRootView;
    private OnChangeListener mListener;
    
    /**
     * 构造
     *
     * @param rootView 根部局参数
     * @param l
     */
    public KeyboardListenerUtil(Activity context, View rootView, OnChangeListener l) {
        mRootView = rootView;
        mListener = l;
        
        if (mRootView == null)
            return;
        
        if (mListener == null)
            return;
        
        mRootView.addOnLayoutChangeListener(this);
        screenHeight = context.getWindowManager().getDefaultDisplay().getHeight();
        keyHeight = screenHeight / 3;
    }
    
    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            mListener.onPopup();
        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            mListener.onClose();
        }
    }
    
    public interface OnChangeListener {
        void onPopup();
        
        void onClose();
    }
}
