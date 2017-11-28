package com.zjrb.bizman.api;


import com.zjrb.bizman.commonlib.BuildConfig;
import com.zjrb.bizman.net_component.ApiRequesterFactory;
import com.zjrb.bizman.net_component.interfaces.OnRequestCallback;
import com.zjrb.bizman.net_component.interfaces.OnRequestListener;
import com.zjrb.bizman.net_component.response.BaseResponse;

/**
 * Api基类，作用把data数据返回给上层，如果需要所有数据，可以在上层实现OnRequestCallback
 */
public class BaseApi implements OnRequestCallback {

    /**
     * 底层请求回调
     */
    protected OnRequestListener mListener;


    public void call(int requestCode,String url,BaseParam param,OnRequestListener listener){
        mListener = listener;
        ApiRequesterFactory.getInstance().get(
                requestCode
                , generalUrl(url)
                , param
                , this
        );
    }

    /**
     * 生成访问url
     * @param suffix
     * @return
     */
    protected static String generalUrl(String suffix) {
        return String.format("%s%s", BuildConfig.BASE_URL, suffix);
    }
    
    /**
     *根据业务需要处理失败或者成功
     * @param responseCode
     * @param requestCode
     * @param response
     */
    @Override
    public void onSuccess(int requestCode, int responseCode, Object response) {
        if (mListener == null) {
            return;
        }
        if (response instanceof BaseResponse) {
            BaseResponse bResponse = (BaseResponse) response;
            if(bResponse.data != null && bResponse.code == 0){
                mListener.onSuccess(requestCode,bResponse.code, bResponse.data);
            }else {
                mListener.onFailure(requestCode,bResponse.code, bResponse.msg);
            }

        }
    }
    
    /**
     * 业务层成功逻辑路由处理
     * @param responseCode
     * @param requestCode
     * @param errMsg
     */
    @Override
    public void onFailure(int requestCode,int responseCode,  String errMsg) {
        if (mListener == null) {
            return;
        }
        if (responseCode == ExceptionCode.REQ_FAILED)
            errMsg = "网络不可用，请检查后重试";
        mListener.onFailure( requestCode,responseCode,errMsg);
    }

}
