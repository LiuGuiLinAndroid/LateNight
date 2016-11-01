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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.GankUser;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

public class UserActivity extends BaseActivity {

    private ListView mListView;
    private List<String> mList = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private ScrollView mZoomScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user);

        initView();
    }

    //初始化View
    private void initView() {
        mZoomScrollView = (ScrollView) findViewById(R.id.mZoomScrollView);
        mZoomScrollView.smoothScrollTo(0, 0);
        mListView = (ListView) findViewById(R.id.mListView);
        getUser();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(mAdapter);
        setListViewHeightBasedOnChildren(mListView);
    }

    //获取到User属性
    private void getUser() {
        //获取到本地属性，前提是已经登录
        GankUser user = BmobUser.getCurrentUser(GankUser.class);
        mList.add("姓名：" + user.getUsername());
        mList.add("年龄：" + user.getAge());
        mList.add("性别：" + (user.isSex() ? "男" : "女"));
        mList.add("身高：" + user.getHeight());
        mList.add("体重：" + user.getWeight());
        mList.add("生日：" + user.getBirthday());
        mList.add("星座：" + user.getConstellation());
        mList.add("学校：" + user.getSchool());
        mList.add("职业：" + user.getOccupation());
        mList.add("简介：" + user.getDesc());
    }

    //重新计算ListView的高度
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
