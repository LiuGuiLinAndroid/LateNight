package com.liuguilin.latenight.entity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.entity
 *  文件名:   MusicData
 *  创建者:   LGL
 *  创建时间:  2016/11/10 15:19
 *  描述：    音乐实体类
 */

public class MusicData {

    //背景图片地址
    private String imgBgUrl;
    //头像地址
    private String imgPhotoUrl;
    //姓名
    private String name;
    //时间
    private String time;
    //播放地址
    private String musicUrl;
    //标题
    private String tvTitle;
    //编辑
    private String tvMessage;
    //头部标题
    private String story_title;
    //内容
    private String tvContent;

    public String getImgBgUrl() {
        return imgBgUrl;
    }

    public void setImgBgUrl(String imgBgUrl) {
        this.imgBgUrl = imgBgUrl;
    }

    public String getImgPhotoUrl() {
        return imgPhotoUrl;
    }

    public void setImgPhotoUrl(String imgPhotoUrl) {
        this.imgPhotoUrl = imgPhotoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public String getTvMessage() {
        return tvMessage;
    }

    public void setTvMessage(String tvMessage) {
        this.tvMessage = tvMessage;
    }

    public String getTvContent() {
        return tvContent;
    }

    public void setTvContent(String tvContent) {
        this.tvContent = tvContent;
    }

    @Override
    public String toString() {
        return "MusicData{" +
                "imgBgUrl='" + imgBgUrl + '\'' +
                ", imgPhotoUrl='" + imgPhotoUrl + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                ", tvTitle='" + tvTitle + '\'' +
                ", tvMessage='" + tvMessage + '\'' +
                ", tvContent='" + tvContent + '\'' +
                '}';
    }

    public String getStory_title() {
        return story_title;
    }

    public void setStory_title(String story_title) {
        this.story_title = story_title;
    }
}
