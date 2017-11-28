package com.zjrb.bizman.utils_component.utils;

import android.content.Context;
import android.media.AudioManager;

/**
 * 媒体音量控制工具类
 * Created by lujialei
 */
public class AudioUtil {
    
    /**
     * 获取最大媒体音量
     */
    public static int getMaxVolume(Context context) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }
    
    /**
     * 获取当前媒体音量
     */
    public static int getCurVolume(Context context) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return am.getStreamVolume(AudioManager.STREAM_MUSIC);
    }
    
    /**
     * 设置媒体音量
     */
    public static void setVolume(Context context, int volume) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        if (volume > maxVolume) {
            volume = maxVolume;
        }
        if (volume < 0) {
            volume = 0;
        }
        am.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
    }
    
}
