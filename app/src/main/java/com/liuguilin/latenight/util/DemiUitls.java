package com.liuguilin.latenight.util;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.view
 *  文件名:   DemiUitls
 *  创建者:   LGL
 *  创建时间:  2016/10/29 13:59
 *  描述：    单位转换
 */

import android.app.Activity;
import android.content.Context;

public class DemiUitls {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getScreenWidth(Activity context) {
        return context.getWindowManager().getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Activity context) {
        return context.getWindowManager().getDefaultDisplay().getHeight();
    }

}
