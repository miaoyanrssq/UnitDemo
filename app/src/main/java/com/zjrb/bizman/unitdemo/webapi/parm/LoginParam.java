package com.zjrb.bizman.unitdemo.webapi.parm;

import android.content.Context;

import com.zjrb.bizman.api.BaseParam;

import java.io.Serializable;

/**
 * Created by lujialei on 2017/11/16.
 */

public class LoginParam extends BaseParam implements Serializable {

    public String username;
    public String password;

}
