package com.zjrb.bizman.unitdemo.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zjrb.bizman.net_component.interfaces.OnRequestListener;
import com.zjrb.bizman.service.AppService;
import com.zjrb.bizman.unitdemo.webapi.api.UserApi;
import com.zjrb.bizman.unitdemo.webapi.parm.LoginParam;

/**
 * Created by lujialei on 2017/11/23.
 */
@Route(path = "/app/service")
public class AppServiceImpl implements AppService{

    @Override
    public void login(Context context,OnRequestListener listener) {
        LoginParam param = new LoginParam(context);
        param.username = "chenshaohua";
        param.password = "12345678";
        UserApi.build().login(param, listener);
    }

    @Override
    public void init(Context context) {

    }
}
