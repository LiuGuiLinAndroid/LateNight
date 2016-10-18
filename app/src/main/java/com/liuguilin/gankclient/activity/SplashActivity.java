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

public class SplashActivity extends AppCompatActivity{

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
