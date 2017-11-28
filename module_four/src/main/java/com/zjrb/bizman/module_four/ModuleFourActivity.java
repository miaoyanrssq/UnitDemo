package com.zjrb.bizman.module_four;

import android.os.Bundle;

import com.zjrb.bizman.ui.BaseActivity;

import butterknife.ButterKnife;

public class ModuleFourActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_four);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().add(R.id.rl_root,new FragmentFour()).commit();
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
