package com.liuguilin.latenight.adapter;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.adapter
 *  文件名:   HorizontalPagerAdapter
 *  创建者:   LGL
 *  创建时间:  2016/10/24 20:09
 *  描述：    主页适配器
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.util.PagerUtils;

import static com.liuguilin.latenight.util.PagerUtils.setupItem;

public class HorizontalPagerAdapter extends PagerAdapter {

    private PagerItemClickListener onPagerItemClickListener;

    private final PagerUtils.LibraryObject[] LIBRARIES = new PagerUtils.LibraryObject[]{
            //天气
            new PagerUtils.LibraryObject(
                    R.drawable.ic_strategy,
                    "天气"
            ),
            //one的推荐
            new PagerUtils.LibraryObject(
                    R.drawable.ic_design,
                    "ONE"
            ),
            //one的阅读
            new PagerUtils.LibraryObject(
                    R.drawable.ic_design,
                    "阅读"
            ),
            //个人中心
            new PagerUtils.LibraryObject(
                    R.drawable.ic_design,
                    "用户"
            ),
            //知乎日报每日最新（轮播图）
            new PagerUtils.LibraryObject(
                    R.drawable.ic_development,
                    "日报"
            ),
            //Android
            new PagerUtils.LibraryObject(
                    R.drawable.ic_development,
                    "Android"
            ),
            //IOS
            new PagerUtils.LibraryObject(
                    R.drawable.ic_development,
                    "IOS"
            ),
            //美女
            new PagerUtils.LibraryObject(
                    R.drawable.ic_development,
                    "妹纸"
            ),
            //小视频
            new PagerUtils.LibraryObject(
                    R.drawable.ic_development,
                    "小视频"
            ),
            //前端
            new PagerUtils.LibraryObject(
                    R.drawable.ic_development,
                    "前端"
            ),
            //音乐
            new PagerUtils.LibraryObject(
                    R.drawable.ic_development,
                    "音乐"
            ),
            //电影
            new PagerUtils.LibraryObject(
                    R.drawable.ic_development,
                    "电影"
            ),
            //小说
            new PagerUtils.LibraryObject(
                    R.drawable.ic_development,
                    "小说"
            ),
            //笑话
            new PagerUtils.LibraryObject(
                    R.drawable.ic_development,
                    "笑话"
            ),
            //设置
            new PagerUtils.LibraryObject(
                    R.drawable.ic_qa,
                    "设置"
            )
    };

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private boolean mIsTwoWay;

    public HorizontalPagerAdapter(final Context context, final boolean isTwoWay) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIsTwoWay = isTwoWay;
    }

    @Override
    public int getCount() {
        return mIsTwoWay ? 6 : LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        if (mIsTwoWay) {
            view = mLayoutInflater.inflate(R.layout.two_way_item, container, false);

            final VerticalInfiniteCycleViewPager verticalInfiniteCycleViewPager =
                    (VerticalInfiniteCycleViewPager) view.findViewById(R.id.vicvp);
            verticalInfiniteCycleViewPager.setAdapter(new VerticalPagerAdapter(mContext));
            verticalInfiniteCycleViewPager.setCurrentItem(position);
        } else {
            view = mLayoutInflater.inflate(R.layout.pager_item, container, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onPagerItemClickListener != null) {
                        onPagerItemClickListener.onPagerItemClickListener(view, position);
                    }
                }
            });
            setupItem(view, LIBRARIES[position]);
        }

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }

    public interface PagerItemClickListener {
        public void onPagerItemClickListener(View view, int postion);
    }

    //实现接口
    public void setOnPagerItemClickListener(PagerItemClickListener onPagerItemClickListener) {
        this.onPagerItemClickListener = onPagerItemClickListener;
    }
}
