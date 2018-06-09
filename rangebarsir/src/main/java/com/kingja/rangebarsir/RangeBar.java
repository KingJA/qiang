package com.kingja.rangebarsir;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Description:TODO
 * Create Time:2018/3/17 17:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RangeBar extends View {

    private static final String TAG = "RangeBar";
    private float normalLineWidth;
    private float sliderSzie;
    private Paint normalLinePaint;
    private Paint sliderPaint;
    private int height;
    private int width;
    private int sliderColor;
    private Slider minSlider;
    private Slider maxSlider;
    private float lastX;
    private float lastY;
    private float rangeMinX;
    private float rangeMaxX;
    private MoveableSlider moveableSlider;
    private int normalLineColor;
    private int sliderImg;
    private Bitmap sliderBitmap;
    private float selectedLineWidth;
    private int selectedLineColor;
    private Paint selectedLinePaint;
    private int minNum;
    private int maxNum;
    private OnRangeChangedListener onRangeChangedListener;
    private int offsetNum;
    private float offsetWidth;
    private int perNums;
    private int minIndex;

    public RangeBar(Context context) {
        this(context, null);
    }

    public RangeBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RangeBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRangeBar(attrs);
        initPaint();
    }

    private void initPaint() {
        normalLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        normalLinePaint.setStrokeWidth(normalLineWidth);
        normalLinePaint.setColor(normalLineColor);

        selectedLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectedLinePaint.setStrokeWidth(selectedLineWidth);
        selectedLinePaint.setColor(selectedLineColor);

        sliderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        sliderPaint.setColor(sliderColor);
    }

    private void initRangeBar(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RangeBar);
        normalLineWidth = a.getDimension(R.styleable.RangeBar_normalLineWidth, 4);
        normalLineColor = a.getColor(R.styleable.RangeBar_normalLineColor, 0x453ddc);

        selectedLineWidth = a.getDimension(R.styleable.RangeBar_selectedLineWidth, 4);
        selectedLineColor = a.getColor(R.styleable.RangeBar_selectedLineColor, 0x453ddc);

        minNum = a.getColor(R.styleable.RangeBar_minNum, 0);
        maxNum = a.getColor(R.styleable.RangeBar_maxNum, 100);
        offsetNum = a.getColor(R.styleable.RangeBar_offsetNum, 10);

        perNums = (maxNum-minNum)/offsetNum;

        sliderSzie = a.getDimension(R.styleable.RangeBar_sliderSzie, dp2px(20));
        sliderColor = a.getColor(R.styleable.RangeBar_sliderColor, 0x453ddc);
        sliderImg = a.getResourceId(R.styleable.RangeBar_sliderImg, -1);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int defaultHeight = (int) sliderSzie;
        int defaultWidth = 300;
        setMeasuredDimension(getExpectSize(defaultWidth, widthMeasureSpec), getExpectSize(defaultHeight,
                heightMeasureSpec));

    }

    private int getExpectSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(size, specSize);
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = getMeasuredHeight();
        width = getMeasuredWidth();
        rangeMinX = sliderSzie;
        rangeMaxX = width - sliderSzie;
        offsetWidth = (rangeMaxX-rangeMinX)/ offsetNum;
        initSlider();
    }

    private void initSlider() {
        sliderBitmap = BitmapFactory.decodeResource(getResources(), sliderImg);

        minSlider = new Slider(sliderSzie * 1.5f, height / 2, sliderSzie);
        maxSlider = new Slider(width - sliderSzie * 1.5f, height / 2, sliderSzie);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*normal line*/
        canvas.drawLine(rangeMinX, height / 2, rangeMaxX, height / 2, normalLinePaint);
        /*selected line*/
        canvas.drawLine(minSlider.getCx(), height / 2, maxSlider.getCx(), height / 2, selectedLinePaint);
        /*slider*/
        canvas.drawCircle(minSlider.getCx(), minSlider.getCy(), sliderSzie / 2, sliderPaint);
        canvas.drawCircle(maxSlider.getCx(), maxSlider.getCy(), sliderSzie / 2, sliderPaint);
    }

    private int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources()
                .getDisplayMetrics());
    }


    public MoveableSlider moveable(float x, float y) {
        if (minSlider.isTouched(x, y)) {
            Log.e(TAG, "右边可动: ");
            return MoveableSlider.MIN_SILDER;
        } else if (maxSlider.isTouched(x, y)) {
            Log.e(TAG, "左边可动: ");
            return MoveableSlider.MAX_SILDER;
        } else {
            Log.e(TAG, "禁用: ");
            return MoveableSlider.DISABLE_MOVE;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = currentX;
                moveableSlider = moveable(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                float offsetX = event.getX() - lastX;
                if (moveableSlider == MoveableSlider.MIN_SILDER && (minSlider.getNextRight(offsetX) < maxSlider
                        .getLeft()) && (minSlider.getNextLeft(offsetX) > sliderSzie / 2)) {
                    minSlider.move(offsetX);
                    Log.e(TAG, "左滑块移动");
                   int currentMinIndex  = (int) ((minSlider.getCx() - rangeMinX) / offsetWidth);
//                    Log.e(TAG, "minIndex="+ minIndex);
                    if (onRangeChangedListener != null&&currentMinIndex!=minIndex) {
                        minIndex=currentMinIndex;
                        onRangeChangedListener.onRnageChanged((int) (minNum+perNums* minIndex),0);
                    }
                } else if (moveableSlider == MoveableSlider.MAX_SILDER && (maxSlider.getNextLeft(offsetX) > minSlider
                        .getRight()) && (maxSlider.getNextRight(offsetX) < width - sliderSzie / 2)) {
                    maxSlider.move(offsetX);
                    Log.e(TAG, "右滑块移动");
                }
                lastX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        invalidate();
        return true;
    }

    enum MoveableSlider {
        MIN_SILDER, MAX_SILDER, DISABLE_MOVE;
    }

    public  interface OnRangeChangedListener {
        void onRnageChanged(int minNum, int maxNum);
    }

    public void setOnRangeChangedListener(OnRangeChangedListener onRangeChangedListener) {
        this.onRangeChangedListener = onRangeChangedListener;
    }
}
