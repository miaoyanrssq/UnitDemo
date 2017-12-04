package com.zjrb.bizman.module_four;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.component_theme.ThemeComponent;
import com.example.component_theme.ThemeAnimation;
import com.zjrb.bizman.event.ThemeEvent;
import com.zjrb.bizman.manager.ActivityLauncher;
import com.zjrb.bizman.theme.ThemeColor;
import com.zjrb.bizman.theme.ThemeMode;
import com.zjrb.bizman.ui.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
        initCheckBox();
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EventBus.getDefault().post(new ThemeEvent(isChecked));
                changeSelfTheme();
            }
        });
    }

    /**
     * 根据当前主题状态设置checkbox
     */
    private void initCheckBox() {
        int themeID = ThemeComponent.getDefault().getCurrentTheme();
       if (themeID == ThemeMode.THEME_DAY) {
            cb.setChecked(true);
        } else {
            cb.setChecked(false);
        }
    }

    /**
     * 切换当前页面的主题ui
     */
    private void changeSelfTheme() {
        ThemeAnimation.show(getActivity());
        refreshUI();
    }

    /**
     * 刷新当前界面
     */
    private void refreshUI() {
        int textColorResID = ThemeComponent.getDefault().getThemeColor(ThemeColor.COLOR_TEXT,getActivity().getTheme());
        int backgbroundColorResID = ThemeComponent.getDefault().getThemeColor(ThemeColor.COLOR_BACKGROUND,getActivity().getTheme());

        fourTv.setTextColor(textColorResID);
        llRoot.setBackgroundResource(backgbroundColorResID);

    }






    @Override
    public void initListener() {
        fourTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityLauncher.gotoTestActivity(getActivity());
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
