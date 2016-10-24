package com.liuguilin.latenight.adapter;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.adapter
 *  文件名:   VerticalPagerAdapter
 *  创建者:   LGL
 *  创建时间:  2016/10/24 20:14
 *  描述：    TODO
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.util.PagerUtils;

import static com.liuguilin.latenight.util.PagerUtils.setupItem;

public class VerticalPagerAdapter extends PagerAdapter {

    private final PagerUtils.LibraryObject[] TWO_WAY_LIBRARIES = new PagerUtils.LibraryObject[]{
            new PagerUtils.LibraryObject(
                    R.drawable.ic_fintech,
                    "Fintech"
            ),
            new PagerUtils.LibraryObject(
                    R.drawable.ic_delivery,
                    "Delivery"
            ),
            new PagerUtils.LibraryObject(
                    R.drawable.ic_social,
                    "Social network"
            ),
            new PagerUtils.LibraryObject(
                    R.drawable.ic_ecommerce,
                    "E-commerce"
            ),
            new PagerUtils.LibraryObject(
                    R.drawable.ic_wearable,
                    "Wearable"
            ),
            new PagerUtils.LibraryObject(
                    R.drawable.ic_internet,
                    "Internet of things"
            )
    };

    private LayoutInflater mLayoutInflater;

    public VerticalPagerAdapter(final Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return TWO_WAY_LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        setupItem(view, TWO_WAY_LIBRARIES[position]);

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
}
