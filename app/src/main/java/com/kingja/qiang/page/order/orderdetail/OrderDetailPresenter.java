package com.kingja.qiang.page.order.orderdetail;

import android.support.annotation.NonNull;

import com.kingja.qiang.model.api.UserApi;
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
public class OrderDetailPresenter implements OrderDetailContract.Presenter {
    private UserApi mApi;
    private OrderDetailContract.View mView;


    @Inject
    public OrderDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull OrderDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getOrderDetail(String orderId) {
        mApi.getUserService().getOrderDetail(orderId).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<OrderDetail>(mView) {
                    @Override
                    protected void onSuccess(OrderDetail orderDetail) {
                        mView.onGetOrderDetailSuccess(orderDetail);
                    }
                });
    }
}