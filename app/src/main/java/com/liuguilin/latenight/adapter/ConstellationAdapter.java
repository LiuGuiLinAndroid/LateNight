package com.liuguilin.latenight.adapter;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.adapter
 *  文件名:   ConstellationAdapter
 *  创建者:   LGL
 *  创建时间:  2016/10/30 10:13
 *  描述：    星座的适配器
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.view.CoverFlow;

public class ConstellationAdapter extends BaseAdapter {
    //背景
    private int mGalleryItemBackground;
    //上下文
    private Context mContext;
    //加载资源图片
    private Integer[] mImageIds = {
            R.drawable.aries1,
            R.drawable.taurus1,
            R.drawable.gemini1,
            R.drawable.cancer1,
            R.drawable.leo1,
            R.drawable.virgo1,
            R.drawable.libra1,
            R.drawable.scorpio1,
            R.drawable.sagittarius1,
            R.drawable.capricorn1,
            R.drawable.aquarius1,
            R.drawable.pisces1};

    public ConstellationAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mImageIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView i = createReflectedImages(mContext,mImageIds[position]);
        i.setLayoutParams(new CoverFlow.LayoutParams(420, 300));
        i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        // 设置的抗锯齿
        BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
        drawable.setAntiAlias(true);
        return i;
    }

    public float getScale(boolean focused, int offset) {
        return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
    }

    //设置图片
    public ImageView createReflectedImages(Context mContext, int imageId) {
        Bitmap originalImage = BitmapFactory.decodeResource(mContext.getResources(), imageId);
        final int reflectionGap = 4;
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, height / 2, width, height / 2, matrix, false);
        Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height / 2), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(originalImage, 0, 0, null);
        Paint deafaultPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap, deafaultPaint);
        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, originalImage
                .getHeight(), 0, bitmapWithReflection.getHeight()
                + reflectionGap, 0x70ffffff, 0x00ffffff, Shader.TileMode.MIRROR);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);
        ImageView imageView = new ImageView(mContext);
        imageView.setImageBitmap(bitmapWithReflection);
        return imageView;
    }
}
