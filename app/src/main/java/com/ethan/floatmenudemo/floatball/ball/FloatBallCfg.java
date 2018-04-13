package com.ethan.floatmenudemo.floatball.ball;

import android.support.annotation.DrawableRes;

public class FloatBallCfg {
    public int mSize;
    public int mIconRes;

    public FloatBallCfg(int size, @DrawableRes int iconRes) {
        mSize = size;
        mIconRes = iconRes;
    }
}