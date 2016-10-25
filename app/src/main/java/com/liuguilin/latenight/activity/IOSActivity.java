package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   IOSActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/25 13:25
 *  描述：    IOS
 */

import android.os.Bundle;

import com.liuguilin.gankclient.R;

public class IOSActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ios);

        initView();
    }

    //初始化View
    private void initView() {

    }
}
