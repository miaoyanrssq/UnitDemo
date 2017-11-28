package com.zjrb.bizman.net_component;


/**
 * Created by lujialei
 */
public interface IApiReuqester {


    /**
     * get请求
     *
     * @param requestCode 请求code
     * @param url         请求地址
     * @param param       请求参数
     * @param listener    回调监听
     * @return request id
     */
    void get(final int requestCode
            , final String url
            , final Object param
            , final OnRequestCallback listener
    );




}
