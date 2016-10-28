package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   AboutActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/28 14:30
 *  描述：    关于
 */

import android.os.Bundle;

import com.liuguilin.gankclient.R;

public class AboutActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
