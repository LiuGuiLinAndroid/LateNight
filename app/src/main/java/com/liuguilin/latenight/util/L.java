package com.liuguilin.latenight.util;

/*
 *  项目名：  lateNight
 *  包名：    com.liuguilin.latenight.util
 *  文件名:   L
 *  创建者:   LGL
 *  创建时间:  2016/10/18 16:05
 *  描述：    Log封装
 */

import android.util.Log;

public class L {

    private static final String TAG = "lateNight";

    public static boolean DEBUG = true;

    public static void i(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (DEBUG)
            Log.v(tag, msg);
    }

    public static void d(Object obj) {
        if (DEBUG)
            Log.d(TAG, obj.toString());
    }


    public static void d(String tag, String msg) {
        if (DEBUG)
            Log.d(tag, "!--->" + msg);
    }

    public static void d(String msg) {
        if (DEBUG)
            Log.d(TAG, msg);
    }

    public static void d(String tag, Exception e) {
        Log.d(tag, e.toString());
    }

    public static void w(String msg) {
        if (DEBUG)
            Log.w(TAG, msg);
    }

    public static void w(String tag, String string) {
        if (DEBUG)
            Log.w(tag, string);
    }

    public static void e(String msg) {
        if (DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG)
            Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (DEBUG)
            Log.e(tag, msg, tr);
    }

    public static void printStackTrace(Exception e) {
        if (DEBUG)
            Log.e(TAG, e.toString());
    }

}
