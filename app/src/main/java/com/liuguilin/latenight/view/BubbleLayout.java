package com.liuguilin.latenight.view;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.view
 *  文件名:   BubbleLayout
 *  创建者:   LGL
 *  创建时间:  2016/11/14 13:08
 *  描述：    海洋气泡
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.liuguilin.latenight.entity.Bubble;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BubbleLayout extends View {

    private List<Bubble> bubbles = new ArrayList<Bubble>();
    //生成随机数
    private Random random = new Random();
    private int width, height;
    private boolean starting = false;

    public BubbleLayout(Context context) {
        super(context);
    }

    public BubbleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BubbleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        if (!starting) {
            starting = true;
            new Thread() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(random.nextInt(3) * 300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Bubble bubble = new Bubble();
                        int radius = random.nextInt(30);
                        while (radius == 0) {
                            radius = random.nextInt(30);
                        }
                        float speedY = random.nextFloat() * 5;
                        while (speedY < 1) {
                            speedY = random.nextFloat() * 5;
                        }
                        bubble.setRadius(radius);
                        bubble.setSpeedY(speedY);
                        bubble.setX(width / 2);
                        bubble.setY(height);
                        float speedX = random.nextFloat() - 0.5f;
                        while (speedX == 0) {
                            speedX = random.nextFloat() - 0.5f;
                        }
                        bubble.setSpeedX(speedX * 2);
                        bubbles.add(bubble);
                    }
                }

                ;
            }.start();
        }
        Paint paint = new Paint();
        //绘制气泡
        paint.reset();
        paint.setColor(0X2AA0F7);//颜色
        paint.setAlpha(45);//设置不透明度：透明为0，完全不透明为255
        List<Bubble> list = new ArrayList<Bubble>(bubbles);
        //依次绘制气泡
        for (Bubble bubble : list) {
            //碰到上边界从数组中移除
            if (bubble.getY() - bubble.getSpeedY() <= 0) {
                bubbles.remove(bubble);
            }
            //碰到左边界从数组中移除
            else if (bubble.getX() - bubble.getRadius() <= 0) {
                bubbles.remove(bubble);
            }
            //碰到右边界从数组中移除
            else if (bubble.getX() + bubble.getRadius() >= width) {
                bubbles.remove(bubble);
            } else {
                int i = bubbles.indexOf(bubble);
                if (bubble.getX() + bubble.getSpeedX() <= bubble.getRadius()) {
                    bubble.setX(bubble.getRadius());
                } else if (bubble.getX() + bubble.getSpeedX() >= width
                        - bubble.getRadius()) {
                    bubble.setX(width - bubble.getRadius());
                } else {
                    bubble.setX(bubble.getX() + bubble.getSpeedX());
                }
                bubble.setY(bubble.getY() - bubble.getSpeedY());

                //海底溢出的甲烷上升过程越来越大（气压减小）
                //鱼类和潜水员吐出的气体却会越变越小（被海水和藻类吸收）
                //如果考虑太多现实情景的话，代码量就会变得很大，也容易出现bug
                bubble.setRadius(bubble.getRadius());

                bubbles.set(i, bubble);
                canvas.drawCircle(bubble.getX(), bubble.getY(),
                        bubble.getRadius(), paint);
            }
        }
        //刷新屏幕
        invalidate();
    }
}
