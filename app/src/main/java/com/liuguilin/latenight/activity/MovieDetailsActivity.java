package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   MovieDetailsActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/9 15:13
 *  描述：    电影详情
 */

import android.content.Intent;
import android.os.Bundle;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.L;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetailsActivity extends BaseActivity {

    private String id;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initView();
    }

    //初始化View
    private void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        getSupportActionBar().setTitle(title);

        RxVolley.get(Constants.ONE_MOIVE_MORE + id, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("movie more:" + t.toString());
                parsingJson(t);
            }
        });

    }

    //解析Json
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
