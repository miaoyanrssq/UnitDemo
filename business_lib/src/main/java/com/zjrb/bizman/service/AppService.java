package com.zjrb.bizman.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.zjrb.bizman.net_component.interfaces.OnRequestListener;

/**
 * Created by lujialei on 2017/11/23.
 */

public interface AppService extends IProvider {
    void login(Context context,OnRequestListener listener);
}
