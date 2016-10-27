package com.liuguilin.latenight.service;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.service
 *  文件名:   ChargingService
 *  创建者:   LGL
 *  创建时间:  2016/10/27 14:44
 *  描述：    充电服务
 */

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class ChargingService extends Service{

    private ChargingReceiver chargingReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        initService();
    }

    //初始化
    private void initService() {
        chargingReceiver = new ChargingReceiver();
        IntentFilter intent = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(chargingReceiver,intent);
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

    private class  ChargingReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case Intent.ACTION_BATTERY_CHANGED:

                    break;
            }
        }
    }
}
