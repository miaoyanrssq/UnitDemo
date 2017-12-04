package com.zjrb.bizman.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjrb.bizman.BaseApplication;
import com.zjrb.bizman.module_one.R;

/**
 * Created by gary on 2017/11/30.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.MyHolder> {


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class MyHolder extends ViewHolder{

        public MyHolder(View itemView) {
            super(itemView);
        }
    }
}
