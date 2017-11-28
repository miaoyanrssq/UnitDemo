package com.zjrb.bizman.module_three;

import android.os.Bundle;

import com.zjrb.bizman.ui.BaseActivity;

public class ModuleThreeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_three);
        getSupportFragmentManager().beginTransaction().add(R.id.rl_root,new ModuleThreeFragment()).commit();
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
