package com.zjrb.bizman.net_component.serialize.impl;


import com.zjrb.bizman.net_component.serialize.ISerializer;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by lujialei
 */
public class GsonSerializer implements ISerializer {
    
    private Gson mGson = new Gson();
    
    @Override
    public String toJson(Map object) {
        return mGson.toJson(object);
    }
    
    @Override
    public String toJson(Object object) {
        return mGson.toJson(object);
    }
    
    @Override
    public Object fromJson(String json, Class<?> cls) {
        return mGson.fromJson(json, cls);
    }
    
    @Override
    public Object fromJson(String json, Type type) {
        return mGson.fromJson(json, type);
    }
}
