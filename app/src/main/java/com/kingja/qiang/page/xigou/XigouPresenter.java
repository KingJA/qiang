package com.kingja.qiang.page.xigou;

import android.support.annotation.NonNull;

import com.kingja.qiang.event.ScenicType;
import com.kingja.qiang.model.api.UserApi;
import com.kingja.qiang.model.entiy.City;
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
public class XigouPresenter implements XigouContract.Presenter {
    private UserApi mApi;
    private XigouContract.View mView;

    @Inject
    public XigouPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull XigouContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getScenicType(String categoryId) {
        mApi.getUserService().getScenicType(categoryId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<List<ScenicType>>(mView) {
                    @Override
                    protected void onSuccess(List<ScenicType> scenicTypes) {
                        mView.onGetScenicTypeSuccess(scenicTypes);
                    }
                });
    }

    @Override
    public void getCity() {
        mApi.getUserService().getCity().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<List<City>>(mView) {
                    @Override
                    protected void onSuccess(List<City> cities) {
                        mView.onGetCitySuccess(cities);
                    }
                });
    }


}