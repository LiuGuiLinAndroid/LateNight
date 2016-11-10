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
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.adapter.SmallVideoAdapter;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.entity.SmallVideoData;
import com.liuguilin.latenight.util.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SmallVideoActivity extends BaseActivity {

    private ListView mListView;
    private ProgressBar progressBar;
    private int count = 1;
    private List<SmallVideoData> mList = new ArrayList<>();
    private SmallVideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_video);

        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.mListView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //暂时没有网络接口
        //getSmallVideo();

        for (int i = 0; i < Constants.videoUrls.length; i++) {
            SmallVideoData data = new SmallVideoData();
            data.setUrl(Constants.videoUrls[i]);
            data.setTitle(Constants.videoTitles[i]);
            data.setImgUrl(Constants.videoThumbs[i]);
            mList.add(data);
        }
        adapter = new SmallVideoAdapter(this, mList);
        mListView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
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
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                String title = json.getString("desc");
                String url = json.getString("url");

                SmallVideoData data = new SmallVideoData();
                data.setUrl(url);
                data.setTitle(title);
                mList.add(data);
            }
            adapter = new SmallVideoAdapter(this, mList);
            mListView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
