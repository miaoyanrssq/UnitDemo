package com.zjrb.bizman.utils_component.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zjrb.bizman.arouter_component.constant.BasicRouterPath;
import com.zjrb.bizman.arouter_component.service.LogService;
import com.zjrb.bizman.utils_component.log.LogUtils;

/**
 * Created by lujialei on 2017/11/27.
 */

@Route(path = BasicRouterPath.LOG_SERVICE_URL)
public class LogServiceImpl implements LogService {

    @Override
    public void i(String tag, String msg) {
        LogUtils.i(tag,msg);
    }

    @Override
    public void d(String tag, String msg) {
        LogUtils.d(tag,msg);
    }


    @Override
    public void v(String tag, String msg) {
        LogUtils.v(tag,msg);
    }

    @Override
    public void e(String tag, String msg) {
        LogUtils.e(tag,msg);
    }

    @Override
    public void w(String tag, String msg) {
        LogUtils.w(tag,msg);
    }

    @Override
    public void json(String tag,String msg) {
        LogUtils.json(tag,msg);
    }

    @Override
    public void xml(String tag, String msg) {
        LogUtils.xml(tag,msg);
    }

    @Override
    public void init(Context context) {

    }
}
