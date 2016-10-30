package com.liuguilin.latenight.activity;

/*
 *  项目名：  lateNight
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   SplashActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/18 15:22
 *  描述：    闪屏页
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.select.SelectConstellationActivity;
import com.liuguilin.latenight.util.SharePreUtils;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class SplashActivity extends AppCompatActivity {

    //Shimmer for Android
    private Shimmer shimmer;
    private ShimmerTextView shimmer_tv;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.HANDLER_WHAT_IS_FIRST:
                    if (isFirst()) {
                        //判断是否自动登录
                        boolean autoLogin = SharePreUtils.getBoolean(SplashActivity.this, Constants.SHARE_AUTO_LOGIN, false);
                        if (autoLogin) {
                            startActivity(new Intent(SplashActivity.this, SelectConstellationActivity.class));
                        } else {
                            startActivity(new Intent(SplashActivity.this, SelectConstellationActivity.class));
                            //startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        }
                    } else {
                        //startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                        startActivity(new Intent(SplashActivity.this, SelectConstellationActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    //初始化View
    private void initView() {
        shimmer_tv = (ShimmerTextView) findViewById(R.id.shimmer_tv);
        shimmer = new Shimmer();
        //启动动画
        shimmer.start(shimmer_tv);
        //启动动画

        handler.sendEmptyMessageDelayed(Constants.HANDLER_WHAT_IS_FIRST, 2000);
    }

    //屏蔽返回键
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shimmer.cancel();
    }

    //判断应用程序是否第一次运行
    private boolean isFirst() {
        boolean isFirst = SharePreUtils.getBoolean(this, Constants.SHARE_IS_FIRST, false);
        if (isFirst) {
            return true;
        } else {
            SharePreUtils.putBoolean(this, Constants.SHARE_IS_FIRST, true);
            return false;
        }
    }
}
