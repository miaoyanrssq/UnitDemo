package com.example.component_theme;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gary on 2017/12/1.
 * theme组件核心类
 */

public class ThemeComponent {

    private static Map<Integer,Integer> sThemeMap;
    private static Map<Integer,CustomColor> sColorMap;
    private static ThemeComponent mInstance;
    private static Context mContext;

    public static ThemeComponent getDefault() {
        if(mInstance == null){
            mInstance = new ThemeComponent();
        }
        return mInstance;
    }

    public void init(Context context){
        mContext = context;
    }

    /**
     * 增加自定义主题
     * @param themeKey theme名字
     * @param themeStyleId them 资源id
     */
    public void addThemeMode(int themeKey, int themeStyleId){
        if(sThemeMap == null){
            sThemeMap = new HashMap<>();
        }
        sThemeMap.put(themeKey,themeStyleId);
    }

    /**
     * 增加自定义颜色
     * @param colorKey
     * @param attrsResId
     */
    public void addThemeColor(int colorKey, int attrsResId){
        if(sColorMap == null){
            sColorMap = new HashMap<>();
        }
        sColorMap.put(colorKey,new CustomColor(attrsResId));
    }

    /**
     * 获取主题中颜色的对应值
     * @param colorKey
     * @param theme
     * @return
     */
    public Integer getThemeColor(int colorKey, Resources.Theme theme){
        CustomColor customColor = sColorMap.get(colorKey);
        return customColor.getColorResoueceInTheme(theme);
    }

    /**
     * 改变StatusBar
     */
    private void changeStatusBarTheme(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = activity.getTheme();
            theme.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(typedValue.resourceId));
        }
    }

    /**
     * 该方法只能在setcontentview之前调用才能生效
     * @param activity
     */
    public void initTheme(Activity activity){
        int styleId = sThemeMap.get(ThemeComponent.getDefault().getCurrentTheme());
        activity.setTheme(styleId);
        //更换当前activity的status bar颜色
        changeStatusBarTheme(activity);
    }

    /**
     * 拿到sp中保存的theme mode
     * @return theme mode
     */
    public int getCurrentTheme(){
        return ThemeSpUtils.getTheme(mContext);
    }


    /**
     * 如果没有设置过主题,那就设置为默认的主题模式
     * @param theme
     */
    public void setDefaultThemeMode(int theme) {
        if(ThemeSpUtils.getTheme(mContext) == -1){
            ThemeSpUtils.saveTheme(mContext,theme);
        }
    }

    /**
     * 保存页面主题模式
     * @param theme
     */
    public void saveThemeMode(Activity activity,int theme) {
        ThemeSpUtils.saveTheme(mContext,theme);
        initTheme(activity);
    }


}
