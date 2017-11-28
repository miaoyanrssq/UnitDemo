package com.zjrb.bizman.net_component.serialize;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by lujialei
 */
public interface ISerializer {
    
    String toJson(Map object);
    
    String toJson(Object object);
    
    Object fromJson(String json, Class<?> cls);
    
    Object fromJson(String json, Type type);
}
