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
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.GlideUtils;
import com.liuguilin.latenight.util.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ONEActivity extends BaseActivity {

    private ViewPager mViewPager;

    private List<String> mListTitle = new ArrayList<>();
    private List<String> mListAuthor = new ArrayList<>();
    private List<String> mListContent = new ArrayList<>();
    private List<String> mListImgUrl = new ArrayList<>();
    private List<String> mListContentUrl = new ArrayList<>();
    private List<String> mListMakettime = new ArrayList<>();

    private List<View> mView = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mViewPager.setOffscreenPageLimit(mView.size());
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
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                String hp_title = json.getString("hp_title");
                String imgUrl = json.getString("hp_img_url");
                String hp_author = json.getString("hp_author");
                String hp_content = json.getString("hp_content");
                String web_url = json.getString("web_url");
                String hp_makettime = json.getString("last_update_date");

                mListTitle.add(hp_title);
                mListAuthor.add(hp_author);
                mListContent.add(hp_content);
                mListImgUrl.add(imgUrl);
                mListContentUrl.add(web_url);
                mListMakettime.add(hp_makettime);
            }

            for (int i = 0; i < mListTitle.size(); i++) {
                //初始化数据
                View view = View.inflate(this, R.layout.pager_one_item, null);
                TextView tv_vol = (TextView) view.findViewById(R.id.tv_vol);
                tv_vol.setText(mListTitle.get(i));
                TextView tv_author = (TextView) view.findViewById(R.id.tv_author);
                tv_author.setText(mListAuthor.get(i));
                TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
                tv_content.setText(mListContent.get(i));
                TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
                tv_date.setText(mListMakettime.get(i));
                ImageView iv_title = (ImageView) view.findViewById(R.id.iv_title);
                GlideUtils.loadImageView(this, mListImgUrl.get(i), iv_title);
                mView.add(view);
            }
            mViewPager.setAdapter(new ONEPagerAdapter());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class ONEPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mView.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(mView.get(position));
            //super.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(mView.get(position));
            return mView.get(position);
        }
    }
}
