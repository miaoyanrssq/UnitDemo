package com.zjrb.bizman.event;

/**
 * Created by gary on 2017/12/8.
 */

public class ThemeEvent extends Event {
    public boolean isDayMode;

    public ThemeEvent(boolean isDayMode) {
        this.isDayMode = isDayMode;
    }
}
