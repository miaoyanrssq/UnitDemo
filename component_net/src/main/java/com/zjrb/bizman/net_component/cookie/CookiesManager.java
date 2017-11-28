package com.zjrb.bizman.net_component.cookie;

import android.content.Context;

import com.zjrb.bizman.net_component.NetComponent;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by lujialei on 2017/11/28.
 */

public class CookiesManager implements CookieJar {

    public CookiesManager() throws Exception {
        cookieStore = new PersistentCookieStore(NetComponent.getContext());
    }

    private final PersistentCookieStore cookieStore;

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }



    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }
}
