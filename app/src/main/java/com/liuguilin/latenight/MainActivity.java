package com.liuguilin.latenight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.activity.AndroidActivity;
import com.liuguilin.latenight.activity.IOSActivity;
import com.liuguilin.latenight.activity.UserActivity;
import com.liuguilin.latenight.activity.WebActivity;
import com.liuguilin.latenight.adapter.HorizontalPagerAdapter;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.entity.GankUser;
import com.liuguilin.latenight.util.L;
import com.liuguilin.latenight.util.SharePreUtils;

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
        infiniteCycleViewPager = (HorizontalInfiniteCycleViewPager) findViewById(R.id.hicvp);
        HorizontalPagerAdapter horizontalPagerAdapter = new HorizontalPagerAdapter(this,false);
        infiniteCycleViewPager.setAdapter(horizontalPagerAdapter);
        horizontalPagerAdapter.setOnPagerItemClickListener(new HorizontalPagerAdapter.PagerItemClickListener() {
            @Override
            public void onPagerItemClickListener(View view, int postion) {
                L.i("position:" + postion);
                switch (postion){
                    case 0:
                        startActivity(new Intent(MainActivity.this, UserActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, WebActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, AndroidActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, IOSActivity.class));
                        break;
                }
            }
        });

        boolean isAutoLogin = SharePreUtils.getBoolean(this, Constants.SHARE_AUTO_LOGIN,false);
        if(isAutoLogin){
            //登录
            String username = SharePreUtils.getString(this,Constants.SHARE_USER_NAME,"");
            String password = SharePreUtils.getString(this,Constants.SHARE_USER_PASSWORD,"");
            GankUser user = new GankUser();
            //....自动登录的逻辑
        }
    }



}
