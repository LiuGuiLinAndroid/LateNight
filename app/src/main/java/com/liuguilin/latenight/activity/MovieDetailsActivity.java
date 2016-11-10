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
import android.widget.ImageView;
import android.widget.TextView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.GlideUtils;
import com.liuguilin.latenight.util.L;
import com.liuguilin.latenight.util.PicassoUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class MovieDetailsActivity extends BaseActivity {

    private String id;
    private String title;

    //头部图片
    private ImageView iv_title_img;
    //红星
    private TextView tv_circle;
    //文章内容
    private TextView tv_content;
    //视频
    private JCVideoPlayerStandard custom_videoplayer_standard;
    //头像
    private CircleImageView profile_image;
    private TextView user_name;
    private TextView user_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initView();
    }

    //初始化View
    private void initView() {

        custom_videoplayer_standard = (JCVideoPlayerStandard) findViewById(R.id.custom_videoplayer_standard);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_circle = (TextView) findViewById(R.id.tv_circle);
        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        user_name = (TextView) findViewById(R.id.user_name);
        user_time = (TextView) findViewById(R.id.user_time);
        iv_title_img = (ImageView) findViewById(R.id.iv_title_img);

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

        RxVolley.get(Constants.ONE_MOIVE_STORY + id + Constants.ONE_MOIVE_STORY_RE, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("Content:" + t);
                getMovieContent(t);
            }
        });

    }

    //获取电影详情
    private void getMovieContent(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonData = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonData.getJSONArray("data");
            JSONObject json = (JSONObject) jsonArray.get(0);
            String content = json.getString("content");
            tv_circle.setText(json.getString("praisenum"));
            user_time.setText(json.getString("input_date"));

            JSONObject jsonUser = json.getJSONObject("user");
            GlideUtils.loadImageView(this, jsonUser.getString("web_url"), profile_image);
            user_name.setText(jsonUser.getString("user_name"));

            tv_content.setText(content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //解析Json
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonData = jsonObject.getJSONObject("data");
            //设置图片
            PicassoUtils.loadImageViewSize(this, jsonData.getString("detailcover"), 650, 300, iv_title_img);
            //设置预览图
            GlideUtils.loadImageView(this, jsonData.getString("indexcover"), custom_videoplayer_standard.thumbImageView);
            custom_videoplayer_standard.setUp(jsonData.getString("video"), JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, "");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
