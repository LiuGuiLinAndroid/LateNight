package com.liuguilin.latenight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.liuguilin.gankclient.R;

public class MainActivity extends AppCompatActivity {

    //launcher卡片
    private HorizontalInfiniteCycleViewPager infiniteCycleViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    //初始化View
    private void initView() {
        //没有机器，暂时不开发
        infiniteCycleViewPager = (HorizontalInfiniteCycleViewPager) findViewById(R.id.hicvp);
    }
}
