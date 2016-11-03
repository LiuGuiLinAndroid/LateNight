package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   JokeActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/3 10:50
 *  描述：    笑话
 */

import android.os.Bundle;

import com.liuguilin.gankclient.R;

public class JokeActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
    }
}
