package com.liuguilin.latenight.entity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.entity
 *  文件名:   JokesData
 *  创建者:   LGL
 *  创建时间:  2016/11/11 13:36
 *  描述：    段子实体类
 */

public class JokesData {

    //时间
    private String time;
    //标题
    private String title;
    //内容
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
