package com.kingja.qiang.page.pay;

import android.support.annotation.NonNull;

import com.kingja.qiang.model.api.UserApi;
import com.kingja.qiang.model.entiy.WeixinPayResult;
import com.kingja.qiang.model.entiy.ResultObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PayPresenter implements PayContract.Presenter {
    private UserApi mApi;
    private PayContract.View mView;

    @Inject
    public PayPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull PayContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void  alipai(String orderId) {
        mApi.getUserService().alipay(orderId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<String>(mView) {
                    @Override
                    protected void onSuccess(String aliPayResult) {
                        mView.onAlipaiSuccess(aliPayResult);
                    }
                });
    }

    @Override
    public void weixinpai(String orderId) {
        mApi.getUserService().weixinpay(orderId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<WeixinPayResult>(mView) {
                    @Override
                    protected void onSuccess(WeixinPayResult weixinPayResult) {
                        mView.onWeixinpaiSuccess(weixinPayResult);
                    }
                });
    }
}