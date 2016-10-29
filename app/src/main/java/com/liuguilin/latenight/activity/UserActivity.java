package com.liuguilin.latenight.activity;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   UserActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/24 21:09
 *  描述：    个人中心
 */

import android.os.Bundle;

import com.liuguilin.gankclient.R;

public class UserActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user);
    }
}
