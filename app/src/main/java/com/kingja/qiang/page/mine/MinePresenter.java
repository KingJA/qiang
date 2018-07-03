package com.kingja.qiang.page.mine;

import android.support.annotation.NonNull;

import com.kingja.qiang.model.api.UserApi;
import com.kingja.qiang.model.entiy.PersonalInfo;
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
public class MinePresenter implements MineContract.Presenter {
    private UserApi mApi;
    private MineContract.View mView;



    @Inject
    public MinePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull MineContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void logout() {
        mApi.getUserService().logout().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onLogoutSuccess();
                    }
                });
    }
}