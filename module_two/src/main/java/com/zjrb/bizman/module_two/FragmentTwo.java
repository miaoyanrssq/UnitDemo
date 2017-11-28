package com.zjrb.bizman.module_two;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjrb.bizman.ui.BaseFragment;

/**
 * Created by lujialei on 2017/11/17.
 */

public class FragmentTwo extends BaseFragment {

    public FragmentTwo() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_two, container, false);
        return mView;
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
