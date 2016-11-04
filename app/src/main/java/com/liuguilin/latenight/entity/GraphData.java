package com.liuguilin.latenight.entity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.entity
 *  文件名:   GraphData
 *  创建者:   LGL
 *  创建时间:  2016/11/3 15:02
 *  描述：    折现数据
 */

public class GraphData {

    private String xValue;
    private int yValue;

    public String getxValue() {
        return xValue;
    }

    public void setxValue(String xValue) {
        this.xValue = xValue;
    }

    public float getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }

    public GraphData(String xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }
}
