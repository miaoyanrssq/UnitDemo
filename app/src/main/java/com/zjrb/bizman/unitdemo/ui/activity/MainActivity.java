package com.zjrb.bizman.unitdemo.ui.activity;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;

import com.zjrb.bizman.BaseApplication;
import com.zjrb.bizman.event.ThemeEvent;
import com.zjrb.bizman.ui.BaseActivity;
import com.zjrb.bizman.unitdemo.R;
import com.zjrb.bizman.unitdemo.ui.widget.BottomBar;
import com.zjrb.bizman.unitdemo.webapi.api.UserApi;
import com.zjrb.bizman.unitdemo.webapi.parm.LoginParam;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import solid.ren.skinlibrary.loader.SkinManager;

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
        EventBus.getDefault().register(this);
        bottomBar.initFragment(getSupportFragmentManager());
    }

    @Subscribe
    public void onThemeChanged(ThemeEvent event){
        if(event.isDayMode){
            SkinManager.getInstance().restoreDefaultTheme();
//            Resources resources = BaseApplication.getContext().getResources();
//            int id = resources.getIdentifier("item_bg","color","com.zjrb.bizman.business_lib");
//            int color = resources.getColor(com.zjrb.bizman.business_lib.R.color.biz_item_bg);
//            AssetManager assetManager = resources.getAssets();
//            String path = getPackageResourcePath();
//            try {
//                String[] paths = assetManager.list(path);
//                int i = 0;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }else {
            SkinManager.getInstance().nightMode();
        }
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
        LoginParam param = new LoginParam();
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
    public void onFailure(int requestCode, int responseCode, String errMsg) {
        super.onFailure(requestCode, responseCode, errMsg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
