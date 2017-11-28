package com.zjrb.bizman.unitdemo.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.zjrb.bizman.manager.ActivityLauncher;
import com.zjrb.bizman.ui.BaseActivity;
import com.zjrb.bizman.unitdemo.R;
import com.zjrb.bizman.unitdemo.ui.widget.BottomBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomBar.OnTabSelectedListener {


    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initVariable();
        initListener();
        loadData();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomBar.initFragment(getSupportFragmentManager());
    }

    @Override
    public void initVariable() {
    }


    @Override
    public void initListener() {
        bottomBar.setOnTabSelectedListener(this);
    }


    @Override
    public void loadData() {
        bottomBar.selectTab(getSupportFragmentManager(), 0);
//        LoginParam param = new LoginParam(this);
//        param.username = "chenshaohua";
//        param.password = "12345678";
//        UserApi.build().login(param,mOnRequestListener);
//        ActivityLauncher.gotoTestActivity(this);
    }

    @Override
    public void onTabSelected(View v, int index) {
        bottomBar.selectTab(getSupportFragmentManager(), index);
    }
}
