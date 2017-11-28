package com.zjrb.bizman.utils_component.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Manifast工具类
 *
 * @author lujialei
 * @date 2017/6/2 11:53
 */
public class ManifastUtil {
    
    /**
     * 获取友盟渠道名
     *
     * @param ctx      此处习惯性的设置为activity，实际上context就可以
     * @param metaName 需要获取得meta名字
     * @return 如果没有获取成功，那么返回值为空
     */
    public static String getMetaValue(Context ctx, String metaName) {
        if (ctx == null) {
            return null;
        }
        String channelName = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = applicationInfo.metaData.getString(metaName);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelName;
    }
}
