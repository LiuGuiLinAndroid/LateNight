package com.liuguilin.latenight.entity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.entity
 *  文件名:   PictureData
 *  创建者:   LGL
 *  创建时间:  2016/11/11 13:54
 *  描述：    图片的实体类
 */

public class PictureData {

    //标题
    private String title;
    //图片
    private String img;
    //时间
    private String time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
