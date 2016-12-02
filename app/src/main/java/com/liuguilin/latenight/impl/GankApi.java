package com.liuguilin.latenight.impl;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.impl
 *  文件名:   GankApi
 *  创建者:   LGL
 *  创建时间:  2016/12/2 15:26
 *  描述：    Gank接口
 */


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GankApi {

    @GET("api/search/query/listview/category/福利/count/50/page/1")
    Call<ResponseBody> getGirl();
}
