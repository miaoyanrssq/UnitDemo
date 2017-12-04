package com.zjrb.bizman.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.component_theme.ThemeComponent;
import com.zjrb.bizman.listener.OnPageInitialize;
import com.zjrb.bizman.net_component.interfaces.OnRequestListener;


/**
 * Created by lujialei on 2017/11/14.
 */

public abstract class BaseActivity extends FragmentActivity implements OnRequestListener,OnPageInitialize {

    protected OnRequestListener mOnRequestListener;

    public BaseActivity() {
        mOnRequestListener = this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();

    }

    /**
     * 初始化theme
     */
    private void initTheme() {
        ThemeComponent.getDefault().initTheme(this);

    }

    @Override
    public void onSuccess(int requestCode, int responseCode, Object response) {
    }

    @Override
    public void onFailure(int requestCode, int responseCode, String errMsg) {
    }


}
