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
import android.net.Uri;
import android.os.IBinder;

import com.liuguilin.latenight.util.L;

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
        //拿到播放地址
        String musicUrl = intent.getStringExtra("url");
        L.i("music url:" + musicUrl);
        playMusic(musicUrl);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //播放在线音乐 调用系统
    private void playMusic(String url) {
        Intent intent = new Intent();
        Uri uri = Uri.parse(url);
        intent.setDataAndType(uri, "audio/*");
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);
    }
}
