package com.liuguilin.latenight.appwidget;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.appwidget
 *  文件名:   ShortcutsProvider
 *  创建者:   LGL
 *  创建时间:  2016/10/27 14:59
 *  描述：    快捷操作小组件
 */

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

public class ShortcutsProvider extends AppWidgetProvider {

    //刷新
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    //第一次添加
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    //最后移除
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    //从屏幕移除
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }
}
