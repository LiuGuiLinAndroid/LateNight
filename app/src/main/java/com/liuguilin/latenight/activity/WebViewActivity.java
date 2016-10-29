package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   WebViewActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/25 9:32
 *  描述：    网页
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.liuguilin.gankclient.R;

public class WebViewActivity extends BaseActivity{

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        initView();
    }

    //初始化
    private void initView() {

        Intent i = getIntent();
        String title = i.getStringExtra("title");
        getSupportActionBar().setTitle(title);
        String content = i.getStringExtra("content");

        mWebView = (WebView) findViewById(R.id.mWebView);
        mWebView.loadDataWithBaseURL(null,content,"text/html", "utf-8", null);
    }

    //返回监听
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
