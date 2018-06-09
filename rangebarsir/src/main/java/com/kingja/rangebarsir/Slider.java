package com.kingja.rangebarsir;

/**
 * Description:TODO
 * Create Time:2018/3/18 15:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Slider {
    private float cx;
    private float cy;
    private float left;
    private float top;
    private float right;
    private float bottom;
    private float size;


    public Slider(float cx, float cy, float size) {
        this.cx = cx;
        this.cy = cy;
        this.size = size;
        left = cx - size / 2;
        top = cy - size / 2;
        right = cx + size / 2;
        bottom = cy + size / 2;
    }


    public float getCx() {
        return cx;
    }

    public float getCy() {
        return cy;
    }

    public boolean isTouched(float x, float y) {
        return (x > left && x < right && y > top && y < bottom);
    }

    public void move(float offset) {
        this.cx += offset;
        left = cx - size / 2;
        right = cx + size / 2;
    }

    public float getNextLeft(float offset) {
        return  cx + offset- size / 2;
    }

    public float getNextRight(float offset) {
        return cx + offset+ size / 2;
    }

    public float getLeft() {
        return left;
    }

    public float getRight() {
        return right;
    }
}
