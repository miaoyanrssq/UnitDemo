package com.zjrb.bizman.module_four;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjrb.bizman.BaseApplication;
import com.zjrb.bizman.event.ThemeEvent;
import com.zjrb.bizman.manager.ActivityLauncher;
import com.zjrb.bizman.ui.BaseFragment;
import com.zjrb.bizman.utils.EventBusHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import solid.ren.skinlibrary.loader.SkinManager;

/**
 * Created by lujialei on 2017/11/23.
 */

public class FragmentFour extends BaseFragment {
    @BindView(R2.id.four_tv)
    TextView fourTv;
    Unbinder unbinder;
    @BindView(R2.id.cb)
    CheckBox cb;
    @BindView(R2.id.ll_root)
    LinearLayout llRoot;
    @BindView(R2.id.rv)
    RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.four_fragment_modulefour, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
    }

    @Override
    public void initVariable() {

    }

    @Override
    public void initView() {
        fourTv.setText("模块四");
        cb.setChecked(!SkinManager.getInstance().isNightMode());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setRecycleChildrenOnDetach(false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(new MyAdapter());
    }


    @Override
    public void initListener() {
        fourTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityLauncher.gotoTestActivity(getActivity());
            }
        });
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EventBusHelper.post(new ThemeEvent(isChecked));
            }
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
