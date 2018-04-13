package com.ethan.floatmenudemo.floatball.manager;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.View;
import android.view.WindowManager;

import com.ethan.floatmenudemo.floatball.ball.FloatBall;
import com.ethan.floatmenudemo.floatball.ball.FloatBallCfg;
import com.ethan.floatmenudemo.floatball.menu.FloatMenu;
import com.ethan.floatmenudemo.floatball.menu.FloatMenuCfg;
import com.ethan.floatmenudemo.floatball.menu.FloatMenuItem;

import java.util.ArrayList;
import java.util.List;


public class FloatBallManager {
    public int mScreenWidth, mScreenHeight;
    private int mStatusBarHeight;

    private OnFloatBallClickListener mFloatBallClickListener;
    private WindowManager mWindowManager;
    private FloatBall floatBall;
    private FloatMenu floatMenu;
    public int floatBallX, floatBallY;
    private boolean isShowing = false;
    private List<FloatMenuItem> menuItems = new ArrayList<>();

    public FloatBallManager(Context application, FloatBallCfg ballCfg) {
        this(application, ballCfg, null);
    }

    public FloatBallManager(Context application, FloatBallCfg ballCfg, FloatMenuCfg menuCfg) {
        int statusBarId = application.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (statusBarId > 0) {
            mStatusBarHeight = application.getResources().getDimensionPixelSize(statusBarId);
        }
        mWindowManager = (WindowManager) application.getSystemService(Context.WINDOW_SERVICE);
        computeScreenSize();
        floatBall = new FloatBall(application, this, ballCfg);
        floatMenu = new FloatMenu(application, this, menuCfg);
    }

    public void buildMenu() {
        inflateMenuItem();
    }

    /**
     * 添加一个菜单条目
     *
     * @param item
     */
    public FloatBallManager addMenuItem(FloatMenuItem item) {
        menuItems.add(item);
        return this;
    }

    public int getMenuItemSize() {
        return menuItems != null ? menuItems.size() : 0;
    }

    /**
     * 设置菜单
     *
     * @param items
     */
    public FloatBallManager setMenu(List<FloatMenuItem> items) {
        menuItems = items;
        return this;
    }

    private void inflateMenuItem() {
        floatMenu.removeAllItemViews();
        for (FloatMenuItem item : menuItems) {
            floatMenu.addItem(item);
        }
    }

    public int getBallSize() {
        return floatBall.getSize();
    }

    private void computeScreenSize() {
        Point point = new Point();
        mWindowManager.getDefaultDisplay().getSize(point);
        mScreenWidth = point.x;
        mScreenHeight = point.y;
        mScreenHeight -= mStatusBarHeight;
    }

    public void show() {
        if (isShowing) {
            return;
        }
        isShowing = true;
        floatBall.setVisibility(View.VISIBLE);
        floatBall.attachToWindow(mWindowManager);
        floatMenu.detachFromWindow(mWindowManager);
    }

    public void closeMenu() {
        floatMenu.closeMenu();
    }

    public void reset() {
        floatBall.setVisibility(View.VISIBLE);
        floatBall.postSleepRunnable();
        floatMenu.detachFromWindow(mWindowManager);
    }

    public void onFloatBallClick() {
        if (menuItems != null && menuItems.size() > 0) {
            floatMenu.attachToWindow(mWindowManager);
        } else {
            if (mFloatBallClickListener != null) {
                mFloatBallClickListener.onFloatBallClick();
            }
        }
    }

    public void hide() {
        if (!isShowing) return;
        isShowing = false;
        floatBall.detachFromWindow(mWindowManager);
        floatMenu.detachFromWindow(mWindowManager);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        computeScreenSize();
        reset();
    }

    public void setOnFloatBallClickListener(OnFloatBallClickListener listener) {
        mFloatBallClickListener = listener;
    }

    public interface OnFloatBallClickListener {
        void onFloatBallClick();
    }
}