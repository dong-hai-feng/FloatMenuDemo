package com.ethan.floatmenudemo.floatball.ball;

import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

public class FloatBallUtil {
    public static WindowManager.LayoutParams getLayoutParams() {
        return getLayoutParams(false);
    }

    public static WindowManager.LayoutParams getLayoutParams(boolean listenBackEvent) {
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
        //如果在整个app或者系统浮动的话,需要以下代码,需要添加浮窗权限
//        final int sdkInt = Build.VERSION.SDK_INT;
//        if (sdkInt < Build.VERSION_CODES.KITKAT) {
//            mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
//        } else if (sdkInt < Build.VERSION_CODES.N_MR1) {
//            mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
//        } else if (sdkInt < Build.VERSION_CODES.O) {
//            mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
//        } else {//8.0以后
//            mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
//        }
        //如果在activity中悬浮不用加权限.
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;

        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        if (listenBackEvent) {
            mLayoutParams.flags = mLayoutParams.flags & ~WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        }

        mLayoutParams.format = PixelFormat.RGBA_8888;
        mLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        return mLayoutParams;
    }
}