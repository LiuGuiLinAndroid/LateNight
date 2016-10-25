package com.liuguilin.latenight.application;

/*
 *  项目名：  lateNight
 *  包名：    com.liuguilin.latenight.application
 *  文件名:   BaseApplication
 *  创建者:   LGL
 *  创建时间:  2016/10/18 15:24
 *  描述：    Application
 */

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.liuguilin.latenight.entity.Constants;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

public class BaseApplication extends MultiDexApplication {

    //初始化
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(getApplicationContext());
        //初始化Bmob
        Bmob.initialize(this, Constants.BMOB_KEY);
        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), Constants.BUGLY_KEY, true);
    }
}
