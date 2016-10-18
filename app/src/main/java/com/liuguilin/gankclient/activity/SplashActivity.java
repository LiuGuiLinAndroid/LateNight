package com.liuguilin.gankclient.activity;

/*
 *  项目名：  GankClient 
 *  包名：    com.liuguilin.gankclient.activity
 *  文件名:   SplashActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/18 15:22
 *  描述：    闪屏页
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liuguilin.gankclient.R;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class SplashActivity extends AppCompatActivity{

    //Shimmer for Android
    private Shimmer shimmer;
    private ShimmerTextView shimmer_tv;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
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
}
