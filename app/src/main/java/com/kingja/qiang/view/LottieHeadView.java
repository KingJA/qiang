package com.kingja.qiang.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.kingja.qiang.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.indicator.PtrTensionIndicator;

/**
 * Description:TODO
 * Create Time:2018/1/29 17:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LottieHeadView extends LinearLayout implements PtrUIHandler {

    private static final String TAG = "LottieHeadView";
    private LottieAnimationView animation_view;
    private PtrFrameLayout mPtrFrameLayout;
    private PtrTensionIndicator mPtrTensionIndicator;

    public LottieHeadView(Context context) {
        this(context, null);
    }

    public LottieHeadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LottieHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        setGravity(Gravity.CENTER);
    }

    private void initView(Context context) {
        View headView = View.inflate(context, R.layout.pull_head, null);
        animation_view = headView.findViewById(R.id.animation_view);
        addView(headView);
    }

    public void setUp(PtrFrameLayout ptrFrameLayout) {
        mPtrFrameLayout = ptrFrameLayout;
        mPtrTensionIndicator = new PtrTensionIndicator();
        mPtrFrameLayout.setPtrIndicator(mPtrTensionIndicator);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        animation_view.loop(true);
        animation_view.playAnimation();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        animation_view.pauseAnimation();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        float overDragPercent = mPtrTensionIndicator.getOverDragPercent();
        if (overDragPercent < 1) {
            animation_view.setProgress(overDragPercent);
        }
    }
}
