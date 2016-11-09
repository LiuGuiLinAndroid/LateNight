package com.liuguilin.latenight.activity;

/*
 *  项目名：  lateNight
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   GuideActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/18 16:26
 *  描述：    引导页
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.gankclient.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    //跳过
    private TextView guide_tv_break;
    //引导
    private ViewPager mViewPager;

    private View view1, view2, view3;
    private List<View> mList = new ArrayList<>();

    private ImageView iv_point1, iv_point2, iv_point3;
    private Button btn_start;
    private TextView tv_content;

    private String[] mStr = {"每一个人都是不平凡的", "别放弃今天所有的努力", "今天和明天都是你的最后一天"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }

    //初始化View
    private void initView() {
        guide_tv_break = (TextView) findViewById(R.id.guide_tv_break);
        guide_tv_break.setOnClickListener(this);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        tv_content = (TextView) findViewById(R.id.tv_content);

        tv_content.setText(mStr[0]);

        iv_point1 = (ImageView) findViewById(R.id.iv_point1);
        iv_point2 = (ImageView) findViewById(R.id.iv_point2);
        iv_point3 = (ImageView) findViewById(R.id.iv_point3);

        setOnOff(true, false, false);

        view1 = View.inflate(this, R.layout.pager_item_one, null);
        view2 = View.inflate(this, R.layout.pager_item_two, null);
        view3 = View.inflate(this, R.layout.pager_item_three, null);
        btn_start = (Button) view3.findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        mList.add(view1);
        mList.add(view2);
        mList.add(view3);

        mViewPager.setAdapter(new GuidePagerAdapter());

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_content.setText(mStr[position]);
                switch (position) {
                    case 0:
                        setOnOff(true, false, false);
                        guide_tv_break.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        setOnOff(false, true, false);
                        guide_tv_break.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        setOnOff(false, false, true);
                        guide_tv_break.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guide_tv_break:
            case R.id.btn_start:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    //适配器
    public class GuidePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(mList.get(position));
            //super.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }

    //设置选中
    private void setOnOff(boolean one, boolean two, boolean three) {
        if (one) {
            iv_point1.setBackgroundResource(R.drawable.point_on);
        } else {
            iv_point1.setBackgroundResource(R.drawable.point_off);
        }

        if (two) {
            iv_point2.setBackgroundResource(R.drawable.point_on);
        } else {
            iv_point2.setBackgroundResource(R.drawable.point_off);
        }

        if (three) {
            iv_point3.setBackgroundResource(R.drawable.point_on);
        } else {
            iv_point3.setBackgroundResource(R.drawable.point_off);
        }
    }
}
