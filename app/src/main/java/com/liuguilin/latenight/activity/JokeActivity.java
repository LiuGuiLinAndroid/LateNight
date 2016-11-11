package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   JokeActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/3 10:50
 *  描述：    笑话
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.fragment.JokesFragment;
import com.liuguilin.latenight.fragment.PictureFragment;

import java.util.ArrayList;
import java.util.List;

public class JokeActivity extends BaseActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        initView();
    }

    //初始化View
    private void initView() {
        mTitles.add("段子");
        mTitles.add("图片");

        mTablayout = (TabLayout) findViewById(R.id.id_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.id_view_pager);

        //预加载
        mViewPager.setOffscreenPageLimit(3);

        //初始化List<Fragment>
        mFragments.add(new JokesFragment());
        mFragments.add(new PictureFragment());

        //给ViewPage设置Adapter
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles.get(position);
            }
        });
        mTablayout.setupWithViewPager(mViewPager);
    }
}
