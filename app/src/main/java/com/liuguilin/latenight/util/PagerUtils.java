package com.liuguilin.latenight.util;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.util
 *  文件名:   PagerUtils
 *  创建者:   LGL
 *  创建时间:  2016/10/24 20:10
 *  描述：    Pager的数据模型
 */

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.gankclient.R;

public class PagerUtils {

    public static void setupItem(final View view, final LibraryObject libraryObject) {
        final TextView txt = (TextView) view.findViewById(R.id.txt_item);
        txt.setText(libraryObject.getTitle());

        final ImageView img = (ImageView) view.findViewById(R.id.img_item);
        img.setImageResource(libraryObject.getRes());
    }

    public static class LibraryObject {

        private String mTitle;
        private int mRes;

        public LibraryObject(final int res, final String title) {
            mRes = res;
            mTitle = title;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(final String title) {
            mTitle = title;
        }

        public int getRes() {
            return mRes;
        }

        public void setRes(final int res) {
            mRes = res;
        }
    }
}
