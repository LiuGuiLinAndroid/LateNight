package com.liuguilin.gankclient.entity;

/*
 *  项目名：  GankClient 
 *  包名：    com.liuguilin.gankclient.entity
 *  文件名:   Constants
 *  创建者:   LGL
 *  创建时间:  2016/10/18 16:28
 *  描述：    常量&接口&方法&字段
 */

import android.graphics.Paint;
import android.widget.TextView;

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

    //Bmob key
    public static final String BMOB_KEY = "4fe28fde75bfc96aad66a887a63592fd";
    //Bugly key
    public static final String BUGLY_KEY = "bd467eaf8d";


    //延时启动
    public static final int HANDLER_WHAT_IS_FIRST = 10001;
    //倒计时
    public static final int HANDLER_WHAT_TIME_DOWN = 10002;


    //第一次运行
    public static final String SHARE_IS_FIRST = "isFirst";
    //自动登录
    public static final String SHARE_AUTO_LOGIN = "autoLogin";



	//密码正则
	public static final String PASSWORD_KEY = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
    //手机号码正则
    public static final String PHONE_KEY = "^((13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$";


    //设置下划线
    public static void setHtml(TextView textView) {
        //设置下划线
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //抗锯齿
        textView.getPaint().setAntiAlias(true);
    }

}
