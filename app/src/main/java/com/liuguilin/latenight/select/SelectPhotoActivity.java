package com.liuguilin.latenight.select;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.select
 *  文件名:   SelectPhotoActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/1 12:46
 *  描述：    选择头像
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liuguilin.gankclient.R;

public class SelectPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_photo);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
