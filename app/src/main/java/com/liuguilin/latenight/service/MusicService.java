package com.liuguilin.latenight.service;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.service
 *  文件名:   MusicService
 *  创建者:   LGL
 *  创建时间:  2016/10/25 13:59
 *  描述：    歌曲服务
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MusicService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
