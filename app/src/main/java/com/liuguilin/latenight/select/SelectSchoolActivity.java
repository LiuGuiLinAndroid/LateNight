package com.liuguilin.latenight.select;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.select
 *  文件名:   SelectSchoolActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/7 16:47
 *  描述：    选择学校
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.L;
import com.liuguilin.latenight.util.SharePreUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectSchoolActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView select_tv_school;
    private ListView mListViewCity;
    private ListView mListViewSchool;
    private List<String> mListCity = new ArrayList<>();
    private List<String> mListSchool = new ArrayList<>();

    private ArrayAdapter<String> mAdapterCity;
    private ArrayAdapter<String> mAdapterSchool;

    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_school);

        initView();
    }

    //初始化
    private void initView() {
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        select_tv_school = (TextView) findViewById(R.id.select_tv_school);
        select_tv_school.setText("清华大学");
        mListViewCity = (ListView) findViewById(R.id.mListCity);
        mListViewSchool = (ListView) findViewById(R.id.mListSchool);

        //请求城市
        RxVolley.get(Constants.PROVINCE_URL, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("city:" + t);
                parsingJsonCity(t);
            }
        });

        mListViewCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取学校列表
                getSchool(position + 1);
            }
        });

        //默认北京
        getSchool(1);

        mListViewSchool.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                select_tv_school.setText(mListSchool.get(position));
            }
        });
    }

    //解析城市数据
    private void parsingJsonCity(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                String province_name = json.getString("province_name");
                mListCity.add(province_name);
            }
            mAdapterCity = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListCity);
            mListViewCity.setAdapter(mAdapterCity);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //解析城市Json
    private void getSchool(int id) {
        RxVolley.get(Constants.SCHOOL_URL + id, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("School:" + t);
                parsingJsonSchool(t);
            }
        });
    }

    //解析学校Json
    private void parsingJsonSchool(String t) {
        mListSchool.clear();
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                String school_name = json.getString("school_name");
                mListSchool.add(school_name);
            }
            mAdapterSchool = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListSchool);
            mListViewSchool.setAdapter(mAdapterSchool);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                SharePreUtils.putString(this,Constants.SHARE_USER_CONSTELLATION,select_tv_school.getText().toString());
                startActivity(new Intent(this,SelectConstellationActivity.class));
                finish();
                break;
        }
    }
}
