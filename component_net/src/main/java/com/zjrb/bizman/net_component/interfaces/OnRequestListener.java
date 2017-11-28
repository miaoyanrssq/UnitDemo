package com.zjrb.bizman.net_component.interfaces;

/**
 * Created by lujialei
 */

public interface OnRequestListener {
    /**
     * 成功返回
     *
     * @param requestCode
     * @param response
     */
    void onSuccess(final int requestCode, final int responseCode, final Object response);
    
    /**
     * 失败返回
     *
     * @param responseCode
     * @param requestCode
     */
    void onFailure(final int requestCode, final int responseCode, String errMsg);

}
