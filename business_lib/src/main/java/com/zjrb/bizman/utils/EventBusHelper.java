package com.zjrb.bizman.utils;

import com.zjrb.bizman.event.Event;

import org.greenrobot.eventbus.EventBus;

/**
 * EventBus帮助类
 */
public class EventBusHelper {
    
    private static EventBus mEventBus = EventBus.getDefault();
    
    public static void register(Object subscriber) {
        if (mEventBus == null)
            return;
        if (subscriber == null)
            return;
        mEventBus.register(subscriber);
    }
    
    public static void unregister(Object subscriber) {
        if (mEventBus == null)
            return;
        if (subscriber == null)
            return;
        mEventBus.unregister(subscriber);
    }
    
    public static void post(Event event) {
        if (mEventBus == null)
            return;
        if (event == null)
            return;

        mEventBus.post(event);
    }
    
}
