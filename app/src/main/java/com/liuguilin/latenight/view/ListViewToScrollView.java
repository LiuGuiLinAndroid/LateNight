package com.liuguilin.latenight.view;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.view
 *  文件名:   ListViewToScrollView
 *  创建者:   LGL
 *  创建时间:  2016/11/10 16:31
 *  描述：    ListView中包含ScrollView
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.ScrollView;

public class ListViewToScrollView extends ListView {

    private ScrollView mScrollView;

    public ListViewToScrollView(Context context) {
        super(context);
    }

    public ListViewToScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ListViewToScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
