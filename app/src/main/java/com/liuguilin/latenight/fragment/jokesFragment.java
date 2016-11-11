package com.liuguilin.latenight.fragment;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.fragment
 *  文件名:   jokesFragment
 *  创建者:   LGL
 *  创建时间:  2016/11/11 13:05
 *  描述：    段子
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.adapter.JokesAdapter;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.entity.JokesData;
import com.liuguilin.latenight.util.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JokesFragment extends Fragment {

    private int page = 1;
    private ListView mListView;
    private ProgressBar progressBar;
    private List<JokesData> mList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jokes, null);
        findView(view);
        return view;
    }

    //初始化View
    private void findView(View view) {

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        mListView = (ListView) view.findViewById(R.id.mListView);

        getJokes();
    }

    //获取段子
    private void getJokes() {
        HttpParams params = new HttpParams();
        params.putHeaders("apikey", Constants.BAIDU_KEY);
        RxVolley.post(Constants.JOKE_TEXT_URL + page, params, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("Joke:" + t);
                parsingJson(t);
            }
        });
    }

    //解析json
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonBody = jsonObject.getJSONObject("showapi_res_body");
            JSONArray jsonArray = jsonBody.getJSONArray("contentlist");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);

                String title = json.getString("title");
                String ct = json.getString("ct");
                String text = json.getString("text");

                JokesData data = new JokesData();
                data.setTitle(title);
                data.setTime(ct);
                data.setContent(text);

                mList.add(data);
            }
            JokesAdapter adapter = new JokesAdapter(getActivity(), mList);
            mListView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
