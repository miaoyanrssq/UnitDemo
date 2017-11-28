package com.zjrb.bizman.manager;

import android.app.Activity;
import android.content.Intent;

import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zjrb.bizman.constant.ActivityRequestCode;
import com.zjrb.bizman.constant.RouterPath;


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

    /**
     * 跳转到主页面
     *
     * @param activity
     */
    public static void gotoTestActivity(Activity activity) {
        ARouter.getInstance()
                .build(RouterPath.ACTIVITY_APP_TEST)
                .navigation(activity,ActivityRequestCode.ACTIVITY_APP_TEST);
    }

}
