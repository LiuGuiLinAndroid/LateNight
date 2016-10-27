package com.liuguilin.latenight.interfaces;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.interfaces
 *  文件名:   IRiseNumber
 *  创建者:   LGL
 *  创建时间:  2016/10/27 22:31
 *  描述：    自动增长TextView接口
 */

import com.liuguilin.latenight.view.RiseNumberTextView;

public interface IRiseNumber {

    //开始播放动画的方法
    public void start();

    //设置小数
    public void withNumber(float number);

    //设置整数
    public void withNumber(int number);

    //设置动画播放时长
    public void setDuration(long duration);

    //设置动画结束监听器
    public void setOnEndListener(RiseNumberTextView.EndListener callback);
}
