package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   AboutActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/28 14:30
 *  描述：    关于
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.liuguilin.gankclient.R;

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    //应用下载
    private LinearLayout ll_app_download;
    //捐赠
    private LinearLayout ll_donation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initView();
    }

    private void initView() {
        ll_app_download = (LinearLayout) findViewById(R.id.ll_app_download);
        ll_app_download.setOnClickListener(this);
        ll_donation = (LinearLayout) findViewById(R.id.ll_donation);
        ll_donation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_app_download:
                startActivity(new Intent(this, AppDownloadActivity.class));
                break;
            case R.id.ll_donation:
                startActivity(new Intent(this, DonationActivity.class));
                break;
        }
    }
}
