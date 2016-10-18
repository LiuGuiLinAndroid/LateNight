package com.liuguilin.gankclient.entity;

/*
 *  项目名：  GankClient 
 *  包名：    com.liuguilin.gankclient.entity
 *  文件名:   Constants
 *  创建者:   LGL
 *  创建时间:  2016/10/18 16:28
 *  描述：    常量&接口&方法&字段
 */

public class Constants {

    //搜索API
    //category 后面可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
    //count 最大 50
    public static final String GANK_SEARCH = "http://gank.io/api/search/query/listview/category/Android/count/10/page/1 ";
    //每日更新API
    public static final String GANK_UPDATE_DAILY = "http://gank.io/api/data/Android/10/1";
    //获取某几日干货网站数据API
    public static final String GANK_FAW_DAY = "http://gank.io/api/history/content/2/1";
    //获取特定日期网站数据API
    public static final String GANK_SPECIFIC_DAY = "http://gank.io/api/history/content/day/2016/05/11";
    //获取发过干货日期API
    //GET
    public static final String GANK_SEND_DATA_LSIT = "http://gank.io/api/day/history";
    //支持提交干货到审核区API
    //POST
    public static final String GANK_POST_DATA = "https://gank.io/api/add2gank";

}
