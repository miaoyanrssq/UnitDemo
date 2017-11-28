package com.zjrb.bizman.module_two;

import android.os.Bundle;

import com.zjrb.bizman.ui.BaseActivity;

public class ModuleTwoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_two);
        getSupportFragmentManager().beginTransaction().add(R.id.rl_root,new FragmentTwo()).commit();
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
