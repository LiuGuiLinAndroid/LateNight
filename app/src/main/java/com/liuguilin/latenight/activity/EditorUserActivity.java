package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   EditorUserActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/7 17:13
 *  描述：    编辑用户
 */

import android.os.Bundle;

import com.liuguilin.gankclient.R;

public class EditorUserActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_user);
    }
}
