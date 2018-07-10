package com.kingja.qiang.service.initialize;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.kingja.qiang.base.App;
import com.kingja.qiang.model.entiy.HotSearch;
import com.kingja.qiang.util.SpSir;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

/**
 * Description:TODO
 * Create Time:2018/7/10 13:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class InitializeService extends IntentService implements InitializeContract.View {

    @Inject
    InitializePresenter initializePresenter;
    public InitializeService(String name) {
        super(name);
    }
    public InitializeService() {
        super("InitializeService");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerInitializeCompnent.builder()
                .appComponent(App.getContext().getAppComponent())
                .build()
                .inject(this);
        initializePresenter.attachView(this);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        initializePresenter.getHotSearch(10);
        Logger.d("onHandleIntent");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("onDestroy");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onGetHotSearch(List<HotSearch> hotSearches) {
        SpSir.getInstance().putHotSearch(new Gson().toJson(hotSearches));
    }
}