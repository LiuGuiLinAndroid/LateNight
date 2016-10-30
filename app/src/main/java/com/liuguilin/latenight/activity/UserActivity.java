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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.view.ZoomScrollView;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends BaseActivity {

    private ListView mListView;
    private List<String> mList = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private ZoomScrollView mZoomScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user);

        initView();
    }

    //初始化View
    private void initView() {
        mZoomScrollView = (ZoomScrollView) findViewById(R.id.mZoomScrollView);
        mZoomScrollView.smoothScrollTo(0, 0);
        mListView = (ListView) findViewById(R.id.mListView);
        for (int i = 0; i < 200 ; i++) {
            mList.add("刘某人程序员：" + i);
        }
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mList);
        mListView.setAdapter(mAdapter);
    }
}
