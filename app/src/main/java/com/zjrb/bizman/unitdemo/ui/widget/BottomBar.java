package com.zjrb.bizman.unitdemo.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjrb.bizman.module_four.FragmentFour;
import com.zjrb.bizman.module_one.FragmentOne;
import com.zjrb.bizman.module_three.ModuleThreeFragment;
import com.zjrb.bizman.module_two.FragmentTwo;
import com.zjrb.bizman.unitdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lujialei on 2017/11/20.
 */

public class BottomBar extends LinearLayout implements View.OnClickListener {

    @BindView(R.id.iv_icon0)
    ImageView ivIcon0;
    @BindView(R.id.ll_item0)
    LinearLayout llItem0;
    @BindView(R.id.iv_icon1)
    ImageView ivIcon1;
    @BindView(R.id.ll_item1)
    LinearLayout llItem1;
    @BindView(R.id.iv_icon2)
    ImageView ivIcon2;
    @BindView(R.id.ll_item2)
    LinearLayout llItem2;
    @BindView(R.id.iv_icon3)
    ImageView ivIcon3;
    @BindView(R.id.ll_item3)
    LinearLayout llItem3;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.tv_title0)
    TextView tvTitle0;
    @BindView(R.id.tv_title3)
    TextView tvTitle3;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;

    private Fragment[] mFragmentArray;
    public BottomBar(Context context) {
        this(context, null);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initListener(context);

    }

    private void initListener(final Context context) {
        llItem0.setOnClickListener(this);
        llItem1.setOnClickListener(this);
        llItem2.setOnClickListener(this);
        llItem3.setOnClickListener(this);

    }

    public void selectTab(FragmentManager fm,int index) {
        if (index == 0) {
            tvTitle0.setTextColor(Color.RED);
            tvTitle1.setTextColor(Color.BLACK);
            tvTitle2.setTextColor(Color.BLACK);
            tvTitle3.setTextColor(Color.BLACK);
        } else if (index == 1) {
            tvTitle0.setTextColor(Color.BLACK);
            tvTitle1.setTextColor(Color.RED);
            tvTitle2.setTextColor(Color.BLACK);
            tvTitle3.setTextColor(Color.BLACK);
        } else if (index == 2) {
            tvTitle0.setTextColor(Color.BLACK);
            tvTitle1.setTextColor(Color.BLACK);
            tvTitle2.setTextColor(Color.RED);
            tvTitle3.setTextColor(Color.BLACK);
        } else if (index == 3) {
            tvTitle0.setTextColor(Color.BLACK);
            tvTitle1.setTextColor(Color.BLACK);
            tvTitle2.setTextColor(Color.BLACK);
            tvTitle3.setTextColor(Color.RED);
        }
        switchFragment(fm,index);
    }

    private void initView(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_bottom_bar, this, true);
        ButterKnife.bind(this, rootView);
    }

    private OnTabSelectedListener mOnTabSelectedListener;

    public void setOnTabSelectedListener(OnTabSelectedListener listener) {
        mOnTabSelectedListener = listener;
    }

    public void initFragment(FragmentManager fragmentManager) {
        if(mFragmentArray == null){
            mFragmentArray = new Fragment[4];
        }
        mFragmentArray[0] = new FragmentOne();
        mFragmentArray[1] = new FragmentTwo();
        mFragmentArray[2] = new ModuleThreeFragment();
        mFragmentArray[3] = new FragmentFour();
        fragmentManager.beginTransaction()
                .add(R.id.fl_content,mFragmentArray[0])
                .add(R.id.fl_content,mFragmentArray[1])
                .add(R.id.fl_content,mFragmentArray[2])
                .add(R.id.fl_content,mFragmentArray[3])
                .hide(mFragmentArray[0])
                .hide(mFragmentArray[1])
                .hide(mFragmentArray[2])
                .hide(mFragmentArray[3])
                .commit();
    }


    private void switchFragment(FragmentManager fm,int index) {
        FragmentTransaction ft = fm.beginTransaction();
        for (int i = 0; i < mFragmentArray.length; i++) {
            if(i == index){
                ft.show(mFragmentArray[i]);
            }else {
                ft.hide(mFragmentArray[i]);
            }
        }
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        if(v == llItem0){
            if (mOnTabSelectedListener != null) {
                mOnTabSelectedListener.onTabSelected(this, 0);
            }
        }else if(v == llItem1){
            if (mOnTabSelectedListener != null) {
                mOnTabSelectedListener.onTabSelected(this, 1);
            }
        }else if(v == llItem2){
            if (mOnTabSelectedListener != null) {
                mOnTabSelectedListener.onTabSelected(this, 2);
            }
        }else if(v == llItem3){
            if (mOnTabSelectedListener != null) {
                mOnTabSelectedListener.onTabSelected(this, 3);
            }
        }

    }

    public interface OnTabSelectedListener {
        void onTabSelected(View v, int index);
    }
}
