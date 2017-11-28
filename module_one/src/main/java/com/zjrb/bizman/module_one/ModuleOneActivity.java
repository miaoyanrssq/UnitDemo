package com.zjrb.bizman.module_one;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.zjrb.bizman.ui.BaseActivity;

/**
 * Created by ASUS on 2017/11/14.
 */

public class ModuleOneActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_one);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.ll_root,new FragmentOne(),"module_one").commit();
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
