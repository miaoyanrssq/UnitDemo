/*
 * Created by DaXiang on  2017 
 * Copyright (c) 2017. All rights reserved.
 *
 * Last modified 17-5-12 下午2:36
 */

package com.zjrb.bizman.utils_component.utils;

import android.net.Uri;

/**
 * url编码
 *
 * @author bingyang
 * @date 2017/5/12 14:22
 */
public class UriEncodeUtil {
    
    public static String encode(String uri) {
        return Uri.encode(uri, "@#&=*+-_.,:!?()/~'%");
    }
}
