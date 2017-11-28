package com.zjrb.bizman.unitdemo.webapi.parm;

import android.content.Context;

import com.zjrb.bizman.api.BaseParam;

/**
 * Created by lujialei on 2017/11/16.
 */

public class LoginParam extends BaseParam {

    public String username;
    public String password;

    public LoginParam(Context context) {
        super(context);
    }
}
