package com.zjrb.bizman.utils_component.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lujialei
 * E-mail: huxs@gold-finance.com.cn
 */

public class AppUtils {
    public static final int STATE_APP_EXIST = 0;
    public static final int STATE_APP_NOT_EXIST = 1;
    public static final int STATE_APP_UNKNOWN_EXIST = 2;
    
    public static final String APP_WEIXIN = "com.tencent.mm";
    public static final String APP_SINA_WEIBO = "com.sina.weibo";
    
    public static int checkAppExistState(Context context, String packageName) {
        try {
            final PackageManager packageManager = context.getPackageManager();//获取packagemanager
            List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);//获取所有已安装程序的包信息
            List<String> pName = new ArrayList<>();//用于存储所有已安装程序的包名
            //从pinfo中将包名字逐一取出，压入pName list中
            if (pinfo != null) {
                for (int i = 0; i < pinfo.size(); i++) {
                    String pn = pinfo.get(i).packageName;
                    pName.add(pn);
                }
            }
            //判断pName中是否有目标程序的包名，有TRUE，没有FALSE
            if (pName.contains(packageName)) {
                return STATE_APP_EXIST;
            } else {
                return STATE_APP_NOT_EXIST;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return STATE_APP_UNKNOWN_EXIST;
    }
    
    /**
     * 打开系统默认浏览器
     * @param context
     * @param url
     */
    public static void gotoBrowser(Context context, String url){
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);
            context.startActivity(intent);
        } catch (Exception e){
            
        }
        
    }
}
