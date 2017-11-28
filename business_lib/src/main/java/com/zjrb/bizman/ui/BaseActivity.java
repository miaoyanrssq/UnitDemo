package com.zjrb.bizman.ui;

import android.support.v4.app.FragmentActivity;

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
    public void onSuccess(int requestCode, int responseCode, Object response) {
    }

    @Override
    public void onFailure(int requestCode, int responseCode, String errMsg) {
    }


}
