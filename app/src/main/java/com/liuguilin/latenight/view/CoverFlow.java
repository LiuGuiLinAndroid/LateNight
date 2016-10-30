package com.liuguilin.latenight.view;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.view
 *  文件名:   CoverFlow
 *  创建者:   LGL
 *  创建时间:  2016/10/30 10:24
 *  描述：    3D画廊选择星座
 */

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

public class CoverFlow extends Gallery {

    private Camera mCamera = new Camera();
    private int mMaxRotationAngle = 50;//60;
    private int mMaxZoom = -380;//-120;
    private int mCoveflowCenter;
    private boolean mAlphaMode = true;
    private boolean mCircleMode = false;

    public CoverFlow(Context context) {
        super(context);
        this.setStaticTransformationsEnabled(true);
    }

    public CoverFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setStaticTransformationsEnabled(true);
    }

    public CoverFlow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setStaticTransformationsEnabled(true);
    }

    public int getMaxRotationAngle() {
        return mMaxRotationAngle;
    }

    public void setMaxRotationAngle(int maxRotationAngle) {
        mMaxRotationAngle = maxRotationAngle;
    }

    public boolean getCircleMode() {
        return mCircleMode;
    }

    public void setCircleMode(boolean isCircle) {
        mCircleMode = isCircle;
    }

    public boolean getAlphaMode() {
        return mAlphaMode;
    }

    public void setAlphaMode(boolean isAlpha) {
        mAlphaMode = isAlpha;
    }

    public int getMaxZoom() {
        return mMaxZoom;
    }

    public void setMaxZoom(int maxZoom) {
        mMaxZoom = maxZoom;
    }

    private int getCenterOfCoverflow() {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2
                + getPaddingLeft();
    }

    private static int getCenterOfView(View view) {
        return view.getLeft() + view.getWidth() / 2;
    }

    protected boolean getChildStaticTransformation(View child, Transformation t) {
        final int childCenter = getCenterOfView(child);
        final int childWidth = child.getWidth();
        int rotationAngle = 0;
        t.clear();
        t.setTransformationType(Transformation.TYPE_MATRIX);
        if (childCenter == mCoveflowCenter) {
            transformImageBitmap((ImageView) child, t, 0);
        } else {
            rotationAngle = (int) (((float) (mCoveflowCenter - childCenter) / childWidth) * mMaxRotationAngle);
            if (Math.abs(rotationAngle) > mMaxRotationAngle) {
                rotationAngle = (rotationAngle < 0) ? -mMaxRotationAngle
                        : mMaxRotationAngle;
            }
            transformImageBitmap((ImageView) child, t, rotationAngle);
        }
        return true;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCoveflowCenter = getCenterOfCoverflow();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 把图像位图的角度通过
     */
    private void transformImageBitmap(ImageView child, Transformation t,
                                      int rotationAngle) {
        mCamera.save();
        final Matrix imageMatrix = t.getMatrix();
        final int imageHeight = child.getLayoutParams().height;
        final int imageWidth = child.getLayoutParams().width;
        final int rotation = Math.abs(rotationAngle);
        mCamera.translate(0.0f, 0.0f, 100.0f);

        // 如视图的角度更少,放大
        if (rotation <= mMaxRotationAngle) {
            float zoomAmount = (float) (mMaxZoom + (rotation * 1.5));
            mCamera.translate(0.0f, 0.0f, zoomAmount);
            if (mCircleMode) {
                if (rotation < 40)
                    mCamera.translate(0.0f, 155, 0.0f);
                else
                    mCamera.translate(0.0f, (255 - rotation * 2.5f), 0.0f);
            }
            if (mAlphaMode) {
                ((ImageView) (child)).setAlpha((int) (255 - rotation * 2.5));
            }
        }
        mCamera.rotateY(rotationAngle);
        mCamera.getMatrix(imageMatrix);
        imageMatrix.preTranslate(-(imageWidth / 2), -(imageHeight / 2));
        imageMatrix.postTranslate((imageWidth / 2), (imageHeight / 2));
        mCamera.restore();
    }
}
