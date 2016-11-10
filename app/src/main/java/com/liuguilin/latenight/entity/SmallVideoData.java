package com.liuguilin.latenight.entity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.entity
 *  文件名:   SmallVideoData
 *  创建者:   LGL
 *  创建时间:  2016/11/2 13:41
 *  描述：    小视频实体
 */

public class SmallVideoData {

    //标题
    private String title;
    //地址
    private String url;
    //图片地址
    private String imgUrl;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
