package com.liuguilin.latenight.interfaces;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.interfaces
 *  文件名:   OnTagClickListener
 *  创建者:   LGL
 *  创建时间:  2016/11/9 11:05
 *  描述：    点击
 */

import android.view.View;

import com.liuguilin.latenight.view.FlowTagLayout;

public interface OnTagClickListener {

    void onItemClick(FlowTagLayout parent, View view, int position);
}
