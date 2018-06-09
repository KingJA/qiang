package com.kingja.qiang.page.wallet;

import android.support.annotation.NonNull;

import com.kingja.qiang.model.api.UserApi;
import com.kingja.qiang.model.entiy.Wallet;
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
public class WalletPresenter implements WalletContract.Presenter {
    private UserApi mApi;
    private WalletContract.View mView;

    @Inject
    public WalletPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull WalletContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getWallet() {
        mApi.getUserService().wallet().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(new ResultObserver<Wallet>(mView) {
            @Override
            protected void onSuccess(Wallet wallet) {
                mView.onGetWalletSuccess(wallet);
            }
        });
    }
}