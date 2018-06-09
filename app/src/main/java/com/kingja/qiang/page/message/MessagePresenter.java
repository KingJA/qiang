package com.kingja.qiang.page.message;

import android.support.annotation.NonNull;

import com.kingja.qiang.model.api.UserApi;
import com.kingja.qiang.model.entiy.Message;
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
public class MessagePresenter implements MessageContract.Presenter {
    private UserApi mApi;
    private MessageContract.View mView;

    @Inject
    public MessagePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull MessageContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getMessage() {
        mApi.getUserService().message().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<List<Message>>(mView) {
                    @Override
                    protected void onSuccess(List<Message> messages) {
                        mView.onGetMessageSuccess(messages);
                    }
                });
    }
}