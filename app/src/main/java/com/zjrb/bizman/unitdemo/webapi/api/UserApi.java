package com.zjrb.bizman.unitdemo.webapi.api;

import com.zjrb.bizman.api.BaseApi;
import com.zjrb.bizman.api.BaseParam;
import com.zjrb.bizman.net_component.OnRequestListener;
import com.zjrb.bizman.unitdemo.webapi.RequestCode;

/**
 * Created by lujialei on 2017/11/16.
 */

public class UserApi extends BaseApi{
    public static final String URL_LOGIN = "/login";

    /**
     * 获取实例
     * @return
     */
    public static UserApi build() {
        return new UserApi();
    }

    /**
     * 登錄
     * @param param
     */
    public void login(BaseParam param, OnRequestListener listener) {
        call(RequestCode.LOGIN,URL_LOGIN,param,listener);
    }


}
