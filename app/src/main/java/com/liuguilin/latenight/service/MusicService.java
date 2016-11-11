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
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.liuguilin.latenight.util.L;

import java.io.IOException;

public class MusicService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener {

    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    //初始化
    private void init() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnBufferingUpdateListener(this);
        mMediaPlayer.setOnPreparedListener(this);
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
        try {
            mMediaPlayer.setDataSource(musicUrl);
            //调用prepareAsync方法，它将在后台开始缓冲音频文件并返回。
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }

    //当MediaPlayer完成播放音频文件时，将调用onCompletion方法。
    @Override
    public void onCompletion(MediaPlayer mp) {
        L.i("onCompletion");
    }

    //如果MediaPlayer出现错误，将调用onError方法。
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                L.i("MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK");
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                L.i("MEDIA_ERROR_SERVER_DIED");
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                L.i("MEDIA_ERROR_UNKNOWN");
                break;
        }
        return false;
    }

    //当MediaPlayer正在缓冲时，将调用该Activity的onBufferingUpdate方法
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        L.i("onBufferingUpdate:" + percent + "%");
    }

    //当完成prepareAsync方法时，将调用onPrepared方法，表明音频准备播放。
    @Override
    public void onPrepared(MediaPlayer mp) {
        L.i("onPrepared");
    }
}
