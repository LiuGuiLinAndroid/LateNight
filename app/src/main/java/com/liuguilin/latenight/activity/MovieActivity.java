package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   MovieActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/2 15:05
 *  描述：    电影
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.adapter.MovieAdapter;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.entity.MovieData;
import com.liuguilin.latenight.util.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends BaseActivity {

    private ListView mListView;
    private List<MovieData> mList = new ArrayList<>();
    private MovieAdapter adapter;
    private ProgressBar progressBar;

    private List<String> mListId = new ArrayList<>();
    private List<String> mListTitle = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.mListView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        getMovie();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MovieActivity.this, MovieDetailsActivity.class);
                intent.putExtra("id", mListId.get(position));
                intent.putExtra("title", mListTitle.get(position));
                startActivity(intent);
            }
        });
    }

    private void getMovie() {
        RxVolley.get(Constants.ONE_MOIVE, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("movie:" + t.toString());
                parsingJson(t);
            }
        });
    }

    //解析
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                String url = json.getString("cover");
                String id = json.getString("id");
                String title = json.getString("title");
                mListTitle.add(title);
                mListId.add(id);
                MovieData data = new MovieData();
                data.setImgUrl(url);
                mList.add(data);
            }
            adapter = new MovieAdapter(this, mList);
            mListView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
