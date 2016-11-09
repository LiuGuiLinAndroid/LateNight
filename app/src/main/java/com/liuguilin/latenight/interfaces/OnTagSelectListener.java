package com.liuguilin.latenight.interfaces;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.interfaces
 *  文件名:   OnTagSelectListener
 *  创建者:   LGL
 *  创建时间:  2016/11/9 11:06
 *  描述：    单选/多选
 */

import com.liuguilin.latenight.view.FlowTagLayout;

import java.util.List;

public interface OnTagSelectListener {

    void onItemSelect(FlowTagLayout parent, int positoin, List<Integer> selectedList);
}
