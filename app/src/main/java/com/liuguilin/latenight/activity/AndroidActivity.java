package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   AndroidActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/25 13:24
 *  描述：    Android
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.adapter.StandardAdapter;
import com.liuguilin.latenight.entity.StandardData;
import com.liuguilin.latenight.util.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AndroidActivity extends AppCompatActivity {

    private ListView mListView;
    private StandardAdapter mAdapter;
    private List<StandardData> mList = new ArrayList<>();
    private int count = 1;
    private ProgressBar progressBar;

    private List<String> mListTitle = new ArrayList<>();
    private List<String> mListContent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initView();
    }

    //初始化View
    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mListView = (ListView) findViewById(R.id.mListView);

        getAndroid();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.i("position:" + position);
                Intent intent = new Intent(AndroidActivity.this, WebViewActivity.class);
                intent.putExtra("title", mListTitle.get(position));
                intent.putExtra("content", mListContent.get(position));
                startActivity(intent);
            }
        });

        mListView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //长按复制链接
                return false;
            }
        });
    }

    //获取数据
    private void getAndroid() {
        String GANK_SEARCH_ANDROID = "http://gank.io/api/search/query/listview/category/Android/count/50/page/" + count;
        RxVolley.get(GANK_SEARCH_ANDROID, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("android:" + t.toString());
                parsingJson(t);
            }
        });
    }

    //解析数据
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArrayResults = jsonObject.getJSONArray("results");
            for (int i = 0; i < jsonArrayResults.length(); i++) {
                JSONObject json = (JSONObject) jsonArrayResults.get(i);
                StandardData data = new StandardData();
                data.setTitle(json.getString("desc"));
                data.setContent(json.getString("readability"));
                data.setTime(json.getString("publishedAt"));
                data.setUrl(json.getString("url"));
                mList.add(data);

                mListTitle.add(json.getString("desc"));
                mListContent.add(json.getString("readability"));
            }
            mAdapter = new StandardAdapter(this, mList);
            mListView.setAdapter(mAdapter);
            progressBar.setVisibility(View.GONE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
