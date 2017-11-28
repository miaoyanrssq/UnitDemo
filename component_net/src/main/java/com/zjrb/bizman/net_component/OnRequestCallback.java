package com.zjrb.bizman.net_component;

/**
 * 基础组件层网络请求回调
 */
public interface OnRequestCallback {
    
    /**
     * 成功返回
     *
     * @param responseCode 回复代码
     * @param requestCode  请求代码
     * @param response     回复实体
     */
    void onSuccess(final int requestCode, final int responseCode, final Object response);
    
    /**
     * 失败返回
     *
     * @param responseCode 回复代码
     * @param requestCode  请求代码
     */
    void onFailure(final int requestCode, final int responseCode, String errMsg);
}