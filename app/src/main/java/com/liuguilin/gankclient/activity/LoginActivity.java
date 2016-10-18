package com.liuguilin.gankclient.activity;

/*
 *  项目名：  GankClient 
 *  包名：    com.liuguilin.gankclient.activity
 *  文件名:   LoginActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/18 16:42
 *  描述：    登录
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liuguilin.gankclient.R;

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //隐藏
        getSupportActionBar().hide();
    }
}
