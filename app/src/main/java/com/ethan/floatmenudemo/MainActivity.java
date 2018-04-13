package com.ethan.floatmenudemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ethan.floatmenudemo.floatball.ball.FloatBallCfg;
import com.ethan.floatmenudemo.floatball.manager.FloatBallManager;
import com.ethan.floatmenudemo.floatball.menu.FloatMenuCfg;
import com.ethan.floatmenudemo.floatball.menu.FloatMenuItem;
import com.ethan.floatmenudemo.floatball.utils.DensityUtil;

public class MainActivity extends AppCompatActivity {
    private FloatBallManager mFloatBallManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFloatBall();
        mFloatBallManager.show();
//        mFloatBallManager.hide();
    }

    private void initFloatBall() {
        //1 初始化悬浮球配置，定义好悬浮球大小和icon的drawable
        int ballSize = DensityUtil.dip2px(this, 65);
        FloatBallCfg ballCfg = new FloatBallCfg(ballSize, R.drawable.cs_main_normal);
        //2 需要显示悬浮菜单
        //2.1 初始化悬浮菜单配置，有菜单item的大小和菜单item的个数
        int menuSize = DensityUtil.dip2px(this, 180);
        int menuItemSize = DensityUtil.dip2px(this, 65);
        FloatMenuCfg menuCfg = new FloatMenuCfg(menuSize, menuItemSize);
        //3 生成floatBallManager
        mFloatBallManager = new FloatBallManager(MainActivity.this, ballCfg, menuCfg);
        addFloatMenuItem();
    }

    private void addFloatMenuItem() {
        FloatMenuItem personItem = new FloatMenuItem(R.drawable.cs_dialing_normal) {
            @Override
            public void action() {
                //打开拨号页面
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "18210097124"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                // 直接拨号
                //intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "18610461110"));
                mFloatBallManager.closeMenu();
            }
        };
        FloatMenuItem walletItem = new FloatMenuItem(R.drawable.cs_chat_normal) {
            @Override
            public void action() {
//                ARouter.getInstance().build(EcnRoute.WebViewActivity)
//                        .withString(Constants.EXTRA_WEB_VIEW_URL, Constants.URL_CUSTOMER_SERVICE)
//                        .withBoolean(Constants.EXTRA_WEB_VIEW_SHOW_SHARE_BTN, false)
//                        .withString(Constants.EXTRA_STATISTICS_TITLE, "客服中心详情页_s")
//                        .withBoolean(Constants.EXTRA_WEB_VIEW_IS_CUSTOMER_SERVICE, true)
//                        .navigation();
                mFloatBallManager.closeMenu();
            }
        };
        mFloatBallManager
                .addMenuItem(walletItem)
                .addMenuItem(personItem)
                .buildMenu();
    }
}
