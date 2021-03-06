package com.kingja.qiang.page.visitor.add;

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
public class VisitorAddPresenter implements VisitorAddContract.Presenter {
    private UserApi mApi;
    private VisitorAddContract.View mView;

    @Inject
    public VisitorAddPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull VisitorAddContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void addVisitor(String name,String mobile, String idcode) {
        mApi.getUserService().addVisitor( name, mobile,  idcode).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<Visitor>(mView) {
                    @Override
                    protected void onSuccess(Visitor visitor) {
                        mView.onAddVisitorSuccess(visitor);
                    }
                });
    }
}