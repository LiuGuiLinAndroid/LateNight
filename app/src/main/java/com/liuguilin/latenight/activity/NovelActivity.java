package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   NovelActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/3 10:47
 *  描述：    小说
 */

import android.os.Bundle;

import com.liuguilin.gankclient.R;

public class NovelActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel);
    }
}
