package com.liuguilin.latenight.activity;

/*
 *  项目名：  lateNight
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   GuideActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/18 16:26
 *  描述：    引导页
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.liuguilin.gankclient.R;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    //跳过
    private TextView guide_tv_break;
    //引导
    private ViewPager mViewPager;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }

    //初始化View
    private void initView() {
        guide_tv_break = (TextView) findViewById(R.id.guide_tv_break);
        guide_tv_break.setOnClickListener(this);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guide_tv_break:
                //你再引导页，说明第一次进入，所有直接跳转登录
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                break;
        }
    }
}
