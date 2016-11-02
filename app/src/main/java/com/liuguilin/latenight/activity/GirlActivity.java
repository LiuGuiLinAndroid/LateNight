package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   GirlActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/25 13:33
 *  描述：    妹子
 */

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.GridView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.adapter.GirlGridAdapter;
import com.liuguilin.latenight.entity.GirlData;
import com.liuguilin.latenight.util.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GirlActivity extends BaseActivity {

    private GridView girlGridView;
    private GirlGridAdapter girlAdapter;
    private List<GirlData> mList = new ArrayList<>();
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);

        initView();
    }

    //初始化View
    private void initView() {
        girlGridView = (GridView) findViewById(R.id.girlGridView);
        //获取数据
        getGirl();

        //监听滑动
        girlGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    L.e("滑到顶部");
                }
                if (visibleItemCount + firstVisibleItem == totalItemCount) {
                    L.e("滑到底部");
                    count++;
                    //更新数据到尾部，分段加载
                }
            }
        });
    }

    //获取美女
    private void getGirl() {
        String GANK_SEARCH_GIRL = "http://gank.io/api/search/query/listview/category/福利/count/50/page/" + count;
        RxVolley.get(GANK_SEARCH_GIRL, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("girl:" + t.toString());
                parsingJson(t);
            }
        });
    }

    //解析图片
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArrayResults = jsonObject.getJSONArray("results");
            for (int i = 0; i < jsonArrayResults.length(); i++) {
                JSONObject json = (JSONObject) jsonArrayResults.get(i);
                GirlData data = new GirlData();
                data.setImgUrl(json.getString("url"));
                data.setTime(json.getString("type"));
                data.setTime(json.getString("desc"));
                mList.add(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        girlAdapter = new GirlGridAdapter(this, mList);
        girlGridView.setAdapter(girlAdapter);
    }
}
