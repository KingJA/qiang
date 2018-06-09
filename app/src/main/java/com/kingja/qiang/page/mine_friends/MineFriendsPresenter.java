package com.kingja.qiang.page.mine_friends;

import android.support.annotation.NonNull;

import com.kingja.qiang.model.api.UserApi;
import com.kingja.qiang.model.entiy.Friend;
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
public class MineFriendsPresenter implements MineFriendsContract.Presenter {
    private UserApi mApi;
    private MineFriendsContract.View mView;

    @Inject
    public MineFriendsPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull MineFriendsContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getMineFriends() {
        mApi.getUserService().getMineFriends().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<List<Friend>>(mView) {
                    @Override
                    protected void onSuccess(List<Friend> friends) {
                        mView.onGetMineFriendsSuccess(friends);
                    }
                });
    }
}