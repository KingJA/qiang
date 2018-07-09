package com.kingja.qiang.page.home;

import android.support.annotation.NonNull;

import com.kingja.qiang.model.api.UserApi;
import com.kingja.qiang.page.home.Ticket;
import com.kingja.qiang.page.home.TicketContract;
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
public class TicketPresenter implements TicketContract.Presenter {
    private UserApi mApi;
    private TicketContract.View mView;

    @Inject
    public TicketPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull TicketContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getTickets(String areaId, String productTypeId, String useDates, String discountRate, String keyword,
                           Integer page, Integer pageSize, Integer status) {
        mApi.getUserService().getTickets(areaId, productTypeId, useDates, discountRate, keyword, page, pageSize,
                status).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<List<Ticket>>(mView) {
                    @Override
                    protected void onSuccess(List<Ticket> tickets) {
                        mView.onGetTicketSuccess(tickets);
                    }
                });
    }

    @Override
    public void getMoreTickets(String areaId, String productTypeId, String useDates, String discountRate, String
            keyword, Integer page, Integer pageSize, Integer status) {
        mApi.getUserService().getTickets(areaId, productTypeId, useDates, discountRate, keyword, page, pageSize,
                status).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<List<Ticket>>(mView) {
                    @Override
                    protected void onSuccess(List<Ticket> tickets) {
                        mView.onGetMoreTicketSuccess(tickets);
                    }
                });
    }

}