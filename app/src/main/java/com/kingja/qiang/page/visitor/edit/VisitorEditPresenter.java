package com.kingja.qiang.page.visitor.edit;

import android.support.annotation.NonNull;

import com.kingja.qiang.model.api.UserApi;
import com.kingja.qiang.page.visitor.Visitor;
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
public class VisitorEditPresenter implements VisitorEditContract.Presenter {
    private UserApi mApi;
    private VisitorEditContract.View mView;

    @Inject
    public VisitorEditPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull VisitorEditContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void editVisitor(String touristId, String name, String mobile, String idcode) {
        mApi.getUserService().editVisitor(touristId, name, mobile, idcode).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<Visitor>(mView) {
                    @Override
                    protected void onSuccess(Visitor visitor) {
                        mView.onEditVisitorSuccess(visitor);
                    }
                });
    }
}