package com.zjrb.bizman.utils_component.tools;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * 当前activity管理栈
 */
public class ActivityStackManager {
    
    private static ActivityStackManager sInstance = new ActivityStackManager();
    private WeakReference<Activity> sCurrentActivityWeakRef;
    
    
    private ActivityStackManager() {
        
    }
    
    public static ActivityStackManager getInstance() {
        return sInstance;
    }
    
    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }
    
    public void setCurrentActivity(Activity activity) {
        sCurrentActivityWeakRef = new WeakReference<Activity>(activity);
    }
}
