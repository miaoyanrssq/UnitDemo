package com.zjrb.bizman.module_one;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjrb.bizman.BaseApplication;

/**
 * Created by gary on 2017/12/8.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MyHolder extends RecyclerView.ViewHolder{

        public MyHolder(View itemView) {
            super(itemView);
        }
    }
}
