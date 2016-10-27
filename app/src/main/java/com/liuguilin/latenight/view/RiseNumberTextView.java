package com.liuguilin.latenight.view;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.view
 *  文件名:   RiseNumberTextView
 *  创建者:   LGL
 *  创建时间:  2016/10/27 22:29
 *  描述：    自动增长TextView
 */

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.interfaces.IRiseNumber;

import java.text.DecimalFormat;

public class RiseNumberTextView extends TextView implements IRiseNumber {

    private static final int STOPPED = 0;

    private static final int RUNNING = 1;

    private int mPlayingState = STOPPED;

    private float number;

    private float fromNumber;

    //动画播放时长
    private long duration = 1500;
    //1.int 2.float
    private int numberType = 2;

    private DecimalFormat fnum;

    private EndListener mEndListener = null;

    final static int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE};

    public RiseNumberTextView(Context context) {
        super(context);
    }

    //使用xml布局文件默认的被调用的构造方法
    public RiseNumberTextView(Context context, AttributeSet attr) {
        super(context, attr);
        setTextColor(context.getResources().getColor(R.color.color_black));
        setTextSize(50);
    }

    public RiseNumberTextView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }

    //判断动画是否正在播放
    public boolean isRunning() {
        return (mPlayingState == RUNNING);
    }

    //跑小数动画
    private void runFloat() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(fromNumber, number);
        valueAnimator.setDuration(duration);
        valueAnimator
                .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        setText(fnum.format(Float.parseFloat(valueAnimator
                                .getAnimatedValue().toString())));
                        if (valueAnimator.getAnimatedFraction() >= 1) {
                            mPlayingState = STOPPED;
                            if (mEndListener != null)
                                mEndListener.onEndFinish();
                        }
                    }
                });
        valueAnimator.start();
    }

    //跑整数动画
    private void runInt() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt((int) fromNumber, (int) number);
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        setText(valueAnimator.getAnimatedValue().toString());
                        if (valueAnimator.getAnimatedFraction() >= 1) {
                            mPlayingState = STOPPED;
                            if (mEndListener != null)
                                mEndListener.onEndFinish();
                        }
                    }
                });
        valueAnimator.start();
    }

    static int sizeOfInt(int x) {
        for (int i = 0; ; i++) {
            if (x <= sizeTable[i])
                return i + 1;
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        fnum = new DecimalFormat("##0.00");
    }

    /**
     * 开始播放动画
     */
    @Override
    public void start() {

        if (!isRunning()) {
            mPlayingState = RUNNING;
            if (numberType == 1)
                runInt();
            else
                runFloat();
        }
    }

    //设置一个小数进来
    @Override
    public void withNumber(float number) {
        this.number = number;
        numberType = 2;
        if (number > 1000) {
            fromNumber = number
                    - (float) Math.pow(10, sizeOfInt((int) number) - 1);
        } else {
            fromNumber = number / 2;
        }
    }

    //设置一个整数进来
    @Override
    public void withNumber(int number) {
        this.number = number;
        numberType = 1;
        if (number > 1000) {
            fromNumber = number
                    - (float) Math.pow(10, sizeOfInt((int) number) - 2);
        } else {
            fromNumber = number / 2;
        }
    }

    //设器置动画时长
    @Override
    public void setDuration(long duration) {
        this.duration = duration;
    }

    //设器置动画结束监听
    @Override
    public void setOnEndListener(EndListener callback) {
        mEndListener = callback;
    }

    //定义动画结束接口
    public interface EndListener {
        /**
         * 当动画播放结束时的回调方法
         */
        public void onEndFinish();
    }
}
