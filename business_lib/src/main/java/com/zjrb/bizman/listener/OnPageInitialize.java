package com.zjrb.bizman.listener;

/**
 * 页面初始化
 */
public interface OnPageInitialize {
    /**
     * 初始化变量
     */
    void initVariable();
    
    /**
     * 初始化控件
     */
    void initView();
    
    /**
     * 注册事件
     */
    void initListener();
    
    /**
     * 加载数据
     */
    void loadData();
}

