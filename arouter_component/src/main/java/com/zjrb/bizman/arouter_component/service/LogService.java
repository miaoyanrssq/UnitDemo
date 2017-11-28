package com.zjrb.bizman.arouter_component.service;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by lujialei on 2017/11/27.
 */

public interface LogService extends IProvider {


    public void i(String tag, String msg);

    public void d(String tag, String msg);

    public void v(String tag, String msg);

    public void e(String tag, String msg);

    public void w(String tag, String msg);

    public void json(String tag,String msg);

    public void xml(String tag, String msg);

}
