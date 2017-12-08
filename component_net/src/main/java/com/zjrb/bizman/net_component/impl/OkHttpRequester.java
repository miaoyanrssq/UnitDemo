package com.zjrb.bizman.net_component.impl;





import com.zjrb.bizman.net_component.cookie.CookiesManager;
import com.zjrb.bizman.net_component.interfaces.IApiReuqester;
import com.zjrb.bizman.net_component.interfaces.OnRequestCallback;
import com.zjrb.bizman.net_component.constant.ExceptionCode;
import com.zjrb.bizman.net_component.response.BaseResponse;
import com.zjrb.bizman.net_component.serialize.SerializerFactory;
import com.zjrb.bizman.net_component.utils.RunUiThread;
import com.zjrb.bizman.utils_component.log.LogUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * OKHTTP 实现类
 * Created by lujialei
 */
public class OkHttpRequester implements IApiReuqester {
    
    private static final String ENCODE_UTF8 = "UTF-8";
    private static final String CONTENT_TYPE = "application/json";
    private static final String GZIP = "gzip";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String DEBUG_FORMAT = "RESP CODE: %1$s, RESQ CODE %2$s, JSON:%3$s, EXCEPTION:%4$s";
    private static final long TIME_OUT = 10*1000;
    private OkHttpClient mClient;

    private OkHttpClient getInstance(long timeout) throws Exception {
        if(mClient == null){
            mClient = new OkHttpClient()
                    .newBuilder()
                    .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                    .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                    .readTimeout(timeout, TimeUnit.MILLISECONDS)
                    .cookieJar(new CookiesManager())
                    .build();
        }
        return mClient;
    }

    /**
     * get方法
     * @param requestCode 请求code
     * @param url         请求地址
     * @param param       请求参数
     * @param listener    回调监听
     */
    @Override
    public void get(final int requestCode, final String url, Object param, final OnRequestCallback listener) {
        try {
            String json = SerializerFactory.getInstance().toJson(param);
            HashMap<String,String> paramMap = (HashMap) SerializerFactory.getInstance().fromJson(json,HashMap.class);
            HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
            for (String key:paramMap.keySet()) {
                urlBuilder.addQueryParameter(key,paramMap.get(key));
            }
            Request request = new Request.Builder()
                    .url(urlBuilder.build())
                    .get()
                    .addHeader("content-type", CONTENT_TYPE)
                    .build();
            LogUtils.d("url", url);
            enqueue(requestCode,request,listener);
        } catch (final Exception e) {
            onEnqueueFailure(e,requestCode,listener);
        }
    }


    /**
     * post方法
     * @param requestCode 请求code
     * @param url         请求地址
     * @param param       请求参数
     * @param listener    回调监听
     */
    @Override
    public void post(final int requestCode, String url, Object param, OnRequestCallback listener) {
        try {
            RequestBody requestBody = RequestBody.create(JSON, SerializerFactory.getInstance().toJson(param));
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("content-type", CONTENT_TYPE)
                    .post(requestBody)
                    .build();
            LogUtils.d("url", url);
            enqueue(requestCode,request,listener);
        } catch (Exception e) {
            onEnqueueFailure(e,requestCode,listener);
        }
    }


    /**
     * 发起异步网络请求
     * @param requestCode
     * @param request
     * @param listener
     * @throws Exception
     */
    public void enqueue( final int requestCode,final Request request ,final OnRequestCallback listener) throws Exception {
        getInstance(TIME_OUT).newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                try {
                    if (call.request().body() != null) {
                        LogUtils.d("ApiRequester", String.format(DEBUG_FORMAT, String.valueOf("0"), String.valueOf(requestCode), String.valueOf(call.request().body().toString()), ""));
                    }
                    RunUiThread.run(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null) {
                                try {
                                    listener.onFailure(requestCode, ExceptionCode.NO_INTERNET,"数据请求失败");
                                } catch (Exception e) {
                                    LogUtils.d("ApiRequester", String.format(DEBUG_FORMAT, String.valueOf("0"), String.valueOf(requestCode), "", e.getMessage()));
                                }
                            }
                        }
                    });
                } catch (final Exception e1) {
                    LogUtils.d("ApiRequester", String.format(DEBUG_FORMAT, String.valueOf("-1"), String.valueOf(requestCode), String.valueOf(e1.getMessage()), ""));
                    RunUiThread.run(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null) {
                                try {
                                    listener.onFailure(requestCode, ExceptionCode.THROW_EXCEPTION, "数据请求失败");
                                } catch (Exception e) {
                                    LogUtils.d("ApiRequester", String.format(DEBUG_FORMAT, String.valueOf("0"), String.valueOf(requestCode), "", e.getMessage()));
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    String json = response.body() == null ? "null" : response.body().string();
                    LogUtils.d("response",json);
                    final Object respObj;
                    respObj = SerializerFactory.getInstance().fromJson(json,BaseResponse.class);
                    RunUiThread.run(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null) {
                                try {
                                    if (200 == response.code()) {
                                        listener.onSuccess(requestCode, response.code(),respObj);
                                    } else if (response.code() >= 500) {
                                        listener.onFailure(requestCode, response.code(), "服务器错误");
                                    } else {
                                        listener.onFailure( requestCode, response.code(),"网络错误");
                                    }
                                } catch (Exception e) {
                                    LogUtils.d("ApiRequester", String.format(DEBUG_FORMAT, String.valueOf("0"), String.valueOf(requestCode), "", e.getMessage()));
                                }
                            }
                        }
                    });
                } catch (final Exception e2) {
                    LogUtils.d("ApiRequester", "Response Exception -> " + String.format(DEBUG_FORMAT, String.valueOf(response.code()), String.valueOf(requestCode), "null", String.valueOf(e2.getMessage())));
                    RunUiThread.run(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null) {
                                try {
                                    listener.onFailure(requestCode, ExceptionCode.THROW_EXCEPTION, "数据解析异常");
                                } catch (Exception e) {
                                    LogUtils.d("ApiRequester", String.format(DEBUG_FORMAT, String.valueOf("0"), String.valueOf(requestCode), "", e.getMessage()));
                                }
                            }
                        }
                    });
                }
            }
        });
    }


    /**
     * 异步网络层请求失败处理
     * @param e
     * @param requestCode
     * @param listener
     */
    private void onEnqueueFailure(final Exception e,final int requestCode,final OnRequestCallback listener) {
        e.printStackTrace();
        LogUtils.d("ApiRequester", String.format(DEBUG_FORMAT, "", String.valueOf(requestCode), String.valueOf(e.getMessage()), ""));
        RunUiThread.run(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    try {
                        listener.onFailure(requestCode,ExceptionCode.THROW_EXCEPTION, e.getMessage());
                    } catch (Exception e) {
                        LogUtils.d("ApiRequester", String.format(DEBUG_FORMAT, String.valueOf("0"), String.valueOf(requestCode), "", e.getMessage()));
                    }
                }
            }
        });
    }
}
