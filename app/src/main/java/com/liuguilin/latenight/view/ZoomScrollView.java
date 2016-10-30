package com.liuguilin.latenight.view;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.view
 *  文件名:   ZoomScrollView
 *  创建者:   LGL
 *  创建时间:  2016/10/30 11:47
 *  描述：    重写让ScrollView有滚动监听(23以前是没有滚动监听的)拦截touch事件，让其支持下拉放大图片
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.util.L;

public class ZoomScrollView extends ScrollView {

    private View zoomView;
    /**
     * 记录上次事件的Y轴
     */
    private float mLastMotionY;
    /**
     * 记录一个滚动了多少距离，通过这个来设置缩放
     */
    private int allScroll = -1;
    /**
     * 控件原本的高度
     */
    private int height = 0;
    /**
     * 被放大的控件id
     */
    private int zoomId;
    /**
     * 最大放大多少像素
     */
    private int maxZoom;
    /**
     * 滚动监听
     */
    private ScrollViewListener scrollViewListener = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            allScroll -= 25;
            if (allScroll < 0) {
                allScroll = 0;
            }
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) zoomView.getLayoutParams();
            lp.height = (int) (height + allScroll / 2);
            zoomView.setLayoutParams(lp);
            if (allScroll != 0) {
                handler.sendEmptyMessageDelayed(1, 10);
            } else {
                allScroll = -1;
            }
        }
    };

    public ZoomScrollView(Context context) {
        super(context);
    }

    public ZoomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ZoomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ZoomScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        zoomView = findViewById(zoomId);
    }

    private void init(AttributeSet attrs) {
        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.ObservableScrollView);
        zoomId = t.getResourceId(R.styleable.ObservableScrollView_zoomId, -1);
        maxZoom = t.getDimensionPixelOffset(R.styleable.ObservableScrollView_maxZoom, 0);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (zoomView == null || maxZoom == 0) {
            return super.dispatchTouchEvent(event);
        }

        final int action = event.getAction();

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            if (allScroll != -1) {
                handler.sendEmptyMessageDelayed(1, 10);
            }
            return super.dispatchTouchEvent(event);
        }

        switch (action) {
            case MotionEvent.ACTION_MOVE: {
                final float y = event.getY();
                final float diff, absDiff;
                diff = y - mLastMotionY;
                mLastMotionY = y;
                absDiff = Math.abs(diff);
                if (allScroll >= 0 && absDiff > 1) {
                    allScroll += diff;

                    if (allScroll < 0) {
                        allScroll = 0;
                    } else if (allScroll > maxZoom) {
                        allScroll = maxZoom;
                    }
                    L.i("allScroll", "allScroll:" + allScroll);
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) zoomView.getLayoutParams();
                    lp.height = (int) (height + allScroll / 2);
                    zoomView.setLayoutParams(lp);
                    if (allScroll == 0) {
                        allScroll = -1;
                    }
                    return false;
                }
                if (isReadyForPullStart()) {
                    if (absDiff > 0) {
                        if (diff >= 1f && isReadyForPullStart()) {
                            mLastMotionY = y;
                            allScroll = 0;
                            height = zoomView.getHeight();
                            return true;
                        }
                    }
                }
                break;
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (allScroll != -1) {
            L.i("ScrollView", "onTouchEvent");
            return false;
        }
        return super.onTouchEvent(ev);
    }


    /**
     * 返回是否可以开始放大
     *
     * @return
     */
    protected boolean isReadyForPullStart() {
        return getScrollY() == 0;
    }


    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    public interface ScrollViewListener {

        void onScrollChanged(ZoomScrollView scrollView, int x, int y, int oldx, int oldy);

    }

    //重写该方法，达到使ListView适应ScrollView的效果
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
