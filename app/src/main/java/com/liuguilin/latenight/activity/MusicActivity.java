package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   MusicActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/25 13:29
 *  描述：    音乐
 */

import android.os.Bundle;

import com.liuguilin.gankclient.R;

public class MusicActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        initView();
    }
    //初始化View
    private void initView() {

    }
}
