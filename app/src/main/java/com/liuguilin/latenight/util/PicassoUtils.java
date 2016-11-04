package com.liuguilin.latenight.util;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.util
 *  文件名:   PicassoUtils
 *  创建者:   LGL
 *  创建时间:  2016/11/3 21:52
 *  描述：    Picasso封装
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class PicassoUtils {

    /**
     * 指定大小加载图片
     *
     * @param mContext   上下文
     * @param path       图片路径
     * @param width      宽
     * @param height     高
     * @param mImageView 控件
     */
    public static void loadImageViewSize(Context mContext, String path, int width, int height, ImageView mImageView) {
        Picasso.with(mContext).load(path).resize(width, height).centerCrop().into(mImageView);
    }


    /**
     * 加载有默认图片
     *
     * @param mContext   上下文
     * @param path       图片路径
     * @param resId      默认图片资源
     * @param mImageView 控件
     */
    public static void loadImageViewHolder(Context mContext, String path, int resId, ImageView mImageView) {
        Picasso.with(mContext).load(path).fit().placeholder(resId).into(mImageView);
    }

    /**
     * 裁剪图片
     *
     * @param mContext   上下文
     * @param path       图片路径
     * @param mImageView 控件
     */
    public static void loadImageViewCrop(Context mContext, String path, ImageView mImageView) {
        Picasso.with(mContext).load(path).transform(new CropImageView()).into(mImageView);
    }

    /**
     * 自定义图片裁剪
     */
    public static class CropImageView implements Transformation {

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap newBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (newBitmap != null) {
                //内存回收
                source.recycle();
            }
            return newBitmap;
        }
        @Override
        public String key() {
            return "lgl";
        }
    }
}
