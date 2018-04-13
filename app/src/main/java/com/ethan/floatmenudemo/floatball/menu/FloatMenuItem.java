package com.ethan.floatmenudemo.floatball.menu;

import android.support.annotation.DrawableRes;

public abstract class FloatMenuItem {
    /**
     * 菜单icon
     */
    public int mDrawableRes;

    public FloatMenuItem(@DrawableRes int drawableRes) {
        this.mDrawableRes = drawableRes;
    }

    /**
     * 点击次菜单执行的操作
     */
    public abstract void action();
}