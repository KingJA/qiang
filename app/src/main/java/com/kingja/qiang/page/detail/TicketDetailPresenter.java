package com.kingja.qiang.page.detail;

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
public class TicketDetailPresenter implements TicketDetailContract.Presenter {
    private UserApi mApi;
    private TicketDetailContract.View mView;

    @Inject
    public TicketDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull TicketDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getTicketDetail(String productId) {
        mApi.getUserService().getTicketDetail(productId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<TicketDetail>(mView) {
                    @Override
                    protected void onSuccess(TicketDetail ticketDetail) {
                        mView.onGetTicketDetailSuccess(ticketDetail);
                    }
                });
    }
}