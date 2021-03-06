package com.zjrb.bizman.ui;

import android.support.v4.app.Fragment;

import com.zjrb.bizman.listener.OnPageInitialize;
import com.zjrb.bizman.net_component.interfaces.OnRequestListener;

import solid.ren.skinlibrary.base.SkinBaseFragment;


/**
 * Created by lujialei on 2017/11/17.
 */

public abstract class BaseFragment extends SkinBaseFragment implements OnRequestListener,OnPageInitialize {

    protected OnRequestListener mOnRequestListener;

    public BaseFragment() {
        mOnRequestListener = this;
    }

    @Override
    public void onSuccess(int requestCode, int responseCode, Object response) {
    }

    @Override
    public void onFailure(int requestCode, int responseCode, String errMsg) {
    }

}
