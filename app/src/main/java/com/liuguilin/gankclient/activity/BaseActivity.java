package com.liuguilin.gankclient.activity;

/*
 *  项目名：  GankClient 
 *  包名：    com.liuguilin.gankclient.activity
 *  文件名:   BaseActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/18 15:26
 *  描述：    Activity基类
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //actionbar显示返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //取消阴影
        getSupportActionBar().setElevation(0);
    }

    //菜单操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
