package com.kingja.qiang.page.discount;

import android.support.annotation.NonNull;

import com.kingja.qiang.model.api.UserApi;
import com.kingja.qiang.model.entiy.Discount;
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
public class DiscountPresenter implements DiscountContract.Presenter {
    private UserApi mApi;
    private DiscountContract.View mView;

    @Inject
    public DiscountPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull DiscountContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }


    @Override
    public void getDiscount() {
        mApi.getUserService().voucher().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(new ResultObserver<List<Discount>>(mView) {
            @Override
            protected void onSuccess(List<Discount> messages) {
                mView.onGetDiscountSuccess(messages);
            }
        });
    }
}