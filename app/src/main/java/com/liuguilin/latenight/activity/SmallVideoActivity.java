package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   SmallVideoActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/2 11:42
 *  描述：    小视频
 */

import android.os.Bundle;
import android.widget.ListView;

import com.liuguilin.gankclient.R;

public class SmallVideoActivity extends BaseActivity{

    private ListView mListView;
    
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_video);
        
        initView();
    }

    private void initView() {

    }
}
