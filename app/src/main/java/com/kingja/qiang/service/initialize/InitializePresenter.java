package com.kingja.qiang.service.initialize;

import android.support.annotation.NonNull;

import com.kingja.qiang.model.api.UserApi;
import com.kingja.qiang.model.entiy.HotSearch;
import com.kingja.qiang.rx.ResultObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class InitializePresenter implements InitializeContract.Presenter {
    private UserApi mApi;
    private InitializeContract.View mView;

    @Inject
    public InitializePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull InitializeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getHotSearch(int limit) {
        mApi.getUserService().getHotSearch(limit).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<List<HotSearch>>(mView) {
                    @Override
                    protected void onSuccess(List<HotSearch> hotSearches) {
                        mView.onGetHotSearch(hotSearches);
                    }
                });
    }


}