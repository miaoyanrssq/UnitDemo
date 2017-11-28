package com.zjrb.bizman.net_component.serialize;


import com.zjrb.bizman.net_component.serialize.impl.GsonSerializer;

/**
 * Created by lujialei
 */
public class SerializerFactory {
    
    private static ISerializer mInstance;
    
    public static ISerializer getInstance() {
        synchronized (SerializerFactory.class) {
            if (mInstance == null) {
                synchronized (SerializerFactory.class) {
                    mInstance = new GsonSerializer();
                }
            }
        }
        return mInstance;
    }
}
