package com.zjrb.bizman.unitdemo.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.example.component_theme.ThemeComponent;
import com.zjrb.bizman.theme.ThemeMode;
import com.zjrb.bizman.event.ThemeEvent;
import com.zjrb.bizman.ui.BaseActivity;
import com.zjrb.bizman.unitdemo.R;
import com.zjrb.bizman.unitdemo.ui.widget.BottomBar;
import com.zjrb.bizman.unitdemo.webapi.api.UserApi;
import com.zjrb.bizman.unitdemo.webapi.parm.LoginParam;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomBar.OnTabSelectedListener {


    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
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

    @Subscribe
    public void onChangeTheme(ThemeEvent themeEvent){
        ThemeComponent.getDefault().saveThemeMode(this,themeEvent.isDayTheme?ThemeMode.THEME_DAY:ThemeMode.THEME_NIGHT);
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
        LoginParam param = new LoginParam(this);
        param.username = "chenshaohua";
        param.password = "12345678";
        UserApi.build().login(param,mOnRequestListener);
//        ActivityLauncher.gotoTestActivity(this);
    }

    @Override
    public void onTabSelected(View v, int index) {
        bottomBar.selectTab(getSupportFragmentManager(), index);
    }

    @Override
    public void onSuccess(int requestCode, int responseCode, Object response) {
        super.onSuccess(requestCode, responseCode, response);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
