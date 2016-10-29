package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   ONEActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/29 16:30
 *  描述：    one的推荐
 */

import android.os.Bundle;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.L;

public class ONEActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        initView();
    }

    private void initView() {

        RxVolley.get(Constants.ONE_FIORST_BOOK, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("one:" + t.toString());
                parsingJson(t);
            }
        });
    }

    //解析数据
    private void parsingJson(String t) {

    }
}
