package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   SmallVideoActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/2 11:42
 *  描述：    小视频
 */

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.util.L;

public class SmallVideoActivity extends BaseActivity {

    private ListView mListView;
    private ProgressBar progressBar;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_video);

        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.mListView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        getSmallVideo();
    }

    //获取视频
    private void getSmallVideo() {
        String url = "http://gank.io/api/search/query/listview/category/休闲视频/count/50/page/" + count;
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("small video:" + t.toString());
                parsingJson(t);
            }
        });
    }

    //解析Json
    private void parsingJson(String t) {

    }
}
