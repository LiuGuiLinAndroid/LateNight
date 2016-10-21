package com.liuguilin.latenight.receiver;

/*
 *  项目名：  lateNight
 *  包名：    com.liuguilin.latenight.receiver
 *  文件名:   ConnectionChangeReceiver
 *  创建者:   LGL
 *  创建时间:  2016/10/18 15:58
 *  描述：    监听网络状态
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ConnectionChangeReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
            //网络不可用就dialog
            Toast.makeText(context,"Network Error",Toast.LENGTH_SHORT).show();
        }
    }
}
