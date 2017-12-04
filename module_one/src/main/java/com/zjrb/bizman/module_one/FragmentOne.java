package com.zjrb.bizman.module_one;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.zjrb.bizman.BaseApplication;
import com.zjrb.bizman.adapter.SimpleAdapter;
import com.zjrb.bizman.constant.RouterPath;
import com.zjrb.bizman.service.AppService;
import com.zjrb.bizman.ui.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by lujialei on 2017/11/17.
 */

public class FragmentOne extends BaseFragment {

    Unbinder unbinder;
    @BindView(R2.id.rv)
    RecyclerView rv;
    @Autowired(name = RouterPath.APP_SERVICE)
    private AppService service;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_one, container, false);
        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void initVariable() {

    }

    @Override
    public void initView() {
//        service = (AppService) ARouter.getInstance().build("/app/service").navigation();
//        if(service == null){
//            Toast.makeText(getContext(),"找不到服务",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        service.login(getContext(), new OnRequestListener() {
//            @Override
//            public void onSuccess(int requestCode, int responseCode, Object response) {
//
//            }
//
//            @Override
//            public void onFailure(int requestCode, int responseCode, String errMsg) {
//
//            }
//        });

        rv.setLayoutManager(new LinearLayoutManager(BaseApplication.getContext()));
        rv.setAdapter(new SimpleAdapter());

    }

    @Override
    public void initListener() {

    }

    @Override
    public void loadData() {

    }

}
