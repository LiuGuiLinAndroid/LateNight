package com.liuguilin.latenight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.activity.WeatherActivity;
import com.liuguilin.latenight.adapter.HorizontalPagerAdapter;
import com.liuguilin.latenight.util.L;

public class MainActivity extends AppCompatActivity {

    //launcher卡片
    private HorizontalInfiniteCycleViewPager infiniteCycleViewPager;
    //添加接口
    private String weather_url = "http://op.juhe.cn/onebox/weather/query?cityname=北京&key=4ea58de8a7573377cec0046f5e2469d5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    //初始化View
    private void initView() {
        infiniteCycleViewPager = (HorizontalInfiniteCycleViewPager) findViewById(R.id.hicvp);
        HorizontalPagerAdapter horizontalPagerAdapter = new HorizontalPagerAdapter(this,false);
        infiniteCycleViewPager.setAdapter(horizontalPagerAdapter);
        horizontalPagerAdapter.setOnPagerItemClickListener(new HorizontalPagerAdapter.PagerItemClickListener() {
            @Override
            public void onPagerItemClickListener(View view, int postion) {
                L.i("position:" + postion);
                switch (postion){
                    case 0:
                        startActivity(new Intent(MainActivity.this, WeatherActivity.class));
                        break;
                }
            }
        });
    }

}
