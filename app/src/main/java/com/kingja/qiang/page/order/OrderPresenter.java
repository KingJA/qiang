package com.kingja.qiang.page.order;

import android.support.annotation.NonNull;

import com.kingja.qiang.model.api.UserApi;
import com.kingja.qiang.model.entiy.Order;
import com.kingja.qiang.model.entiy.ResultObserver;

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
public class OrderPresenter implements OrderContract.Presenter {
    private UserApi mApi;
    private OrderContract.View mView;


    @Inject
    public OrderPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull OrderContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getOrders(Integer page, Integer pageSize, Integer status) {
        mApi.getUserService().getOrders(page, pageSize, status).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<Order>>(mView) {
                    @Override
                    protected void onSuccess(List<Order> orders) {
                        mView.onGetOrdersSuccess(orders);
                    }
                });
    }
}