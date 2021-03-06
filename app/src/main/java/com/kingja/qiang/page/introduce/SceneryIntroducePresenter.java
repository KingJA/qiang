package com.kingja.qiang.page.introduce;

import android.support.annotation.NonNull;

import com.kingja.qiang.model.api.UserApi;
import com.kingja.qiang.page.detail.TicketDetail;
import com.kingja.qiang.page.detail.TicketDetailContract;
import com.kingja.qiang.rx.ResultObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SceneryIntroducePresenter implements SceneryIntroduceContract.Presenter {
    private UserApi mApi;
    private SceneryIntroduceContract.View mView;

    @Inject
    public SceneryIntroducePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull SceneryIntroduceContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getSceneryIntroduce(String scenicId) {
        mApi.getUserService().getSceneryIntroduce(scenicId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<SceneryIntroduce>(mView) {
                    @Override
                    protected void onSuccess(SceneryIntroduce sceneryIntroduce) {
                        mView.onGetSceneryIntroduceSuccess(sceneryIntroduce);
                    }
                });
    }
}