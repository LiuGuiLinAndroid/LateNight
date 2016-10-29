package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   ReadingActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/29 13:43
 *  描述：    美文
 */

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.GlideUtils;
import com.liuguilin.latenight.util.L;
import com.liuguilin.latenight.view.ImageCycleView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReadingActivity extends BaseActivity {

    private ImageCycleView imageCycleView;
    //标题
    private ArrayList<String> imageDescList = new ArrayList<>();
    //地址
    private ArrayList<String> urlList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        initView();
    }

    //初始化View
    private void initView() {
        imageCycleView = (ImageCycleView) findViewById(R.id.cycleView);
		//三种Style切换
        imageCycleView.setCycle_T(ImageCycleView.CYCLE_T.CYCLE_VIEW_THREE_SCALE);

        RxVolley.get(Constants.ONE_READING_IMG, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("reading:" + t.toString());
                parsingJson(t);
            }
        });
    }

    //解析图片
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArrayData = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArrayData.length(); i++) {
                JSONObject json = (JSONObject) jsonArrayData.get(i);
                imageDescList.add(json.getString("bottom_text"));
                urlList.add(json.getString("cover"));
            }
            initCarsuelView(imageDescList, urlList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //初始化轮播
    public void initCarsuelView(ArrayList<String> imageDescList, ArrayList<String> urlList) {
        LinearLayout.LayoutParams cParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getScreenHeight(ReadingActivity.this) * 3 / 10);
        imageCycleView.setLayoutParams(cParams);
        ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void onImageClick(int position, View imageView) {
                Toast.makeText(ReadingActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                GlideUtils.loadImageView(ReadingActivity.this,imageURL,imageView);
            }
        };
        //设置数据
        imageCycleView.setImageResources(imageDescList, urlList, mAdCycleViewListener);
        //是否隐藏底部
        imageCycleView.hideBottom(false);
        imageCycleView.startImageCycle();
    }

    //获取屏幕高宽
    public static int getScreenHeight(Context context) {
        if (null == context) {
            return 0;
        }
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
}
