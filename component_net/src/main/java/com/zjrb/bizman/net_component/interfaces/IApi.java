package com.zjrb.bizman.net_component.interfaces;

import com.zjrb.bizman.net_component.api.BaseParam;

/**
 * Created by lujialei on 2017/11/24.
 */

public interface IApi {
    void call(int requestCode, String url, BaseParam param, OnRequestListener listener);
}
