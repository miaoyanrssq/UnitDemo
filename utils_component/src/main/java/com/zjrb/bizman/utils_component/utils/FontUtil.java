package com.zjrb.bizman.utils_component.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.widget.TextView;

/**
 * 字体工具箱
 * Created by lujialei
 */
public class FontUtil {
    
    public static void setBold(TextView textView) {
        TextPaint tp = textView.getPaint();
        tp.setFakeBoldText(true);
    }
    
    public static Typeface getDIN1451EF_EngAltTTF(Context context) {
        return Typeface.createFromAsset(context.getApplicationContext().getResources().getAssets(), "fonts/DIN1451EF-EngAlt.ttf");
    }
}
