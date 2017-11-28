package com.zjrb.bizman.unitdemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zjrb.bizman.constant.RouterPath;
import com.zjrb.bizman.ui.BaseActivity;
import com.zjrb.bizman.unitdemo.R;
import com.zjrb.bizman.utils_component.log.LogUtils;

/**
 * Created by lujialei on 2017/11/23.
 */

@Route(path = RouterPath.ACTIVITY_APP_TEST)
public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initVariable() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void loadData() {

    }
}
