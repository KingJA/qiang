package com.kingja.qiang.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.kingja.qiang.R;
import com.kingja.qiang.util.GoUtil;

/**
 * Description:TODO
 * Create Time:2018/5/14 13:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SplashActivity extends AppCompatActivity {
    private int DELAY_MILLIS = 2000;
    private Handler dispatchHander;
    private DispatcherRunnable dispatcherRunnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        dispatchHander = new Handler();
        dispatcherRunnable = new DispatcherRunnable();
        dispatchHander.postDelayed(dispatcherRunnable, DELAY_MILLIS);
    }


    class DispatcherRunnable implements Runnable {

        @Override
        public void run() {
            GoUtil.goActivityAndFinish(SplashActivity.this, GuideActivity.class);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispatchHander.removeCallbacks(dispatcherRunnable);
    }
}
