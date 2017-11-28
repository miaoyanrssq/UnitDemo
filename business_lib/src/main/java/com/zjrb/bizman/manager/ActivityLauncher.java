package com.zjrb.bizman.manager;

import android.app.Activity;


/**
 * 跨模块进行启动页面调用写入该类 {@link ActivityLauncher}
 * Activty启动管理类
 */
public class ActivityLauncher {

    /**
     * 检查参数
     *
     * @param activity
     * @return
     */
    private static boolean check(Activity activity) {
        if (activity == null)
            return false;

        return !activity.isFinishing();
    }

}
