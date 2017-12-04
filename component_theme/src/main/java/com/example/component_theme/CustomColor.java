package com.example.component_theme;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by gary on 2017/12/4.
 */

public class CustomColor {
    public TypedValue typedValue;
    public int attrsResId;

    public CustomColor(int attrsResId) {
        this.typedValue = new TypedValue();
        this.attrsResId = attrsResId;
    }

    /**
     * 获取主题中对应的颜色资源文件
     * @param theme
     * @return
     */
    public int getColorResoueceInTheme(Resources.Theme theme){
        theme.resolveAttribute(attrsResId,typedValue,true);
        return typedValue.resourceId;
    }
}
