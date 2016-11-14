package com.liuguilin.latenight.entity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.entity
 *  文件名:   Bubble
 *  创建者:   LGL
 *  创建时间:  2016/11/14 13:10
 *  描述：    海洋气泡实体
 */

public class Bubble {

    //气泡半径
    private float radius;
    //上升速度
    private float speedY;
    //平移速度
    private float speedX;
    //气泡x坐标
    private float x;
    // 气泡y坐标
    private float y;

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    @Override
    public String toString() {
        return "Bubble{" +
                "radius=" + radius +
                ", speedY=" + speedY +
                ", speedX=" + speedX +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
