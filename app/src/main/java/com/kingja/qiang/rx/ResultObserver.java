package com.kingja.qiang.rx;

import android.util.Log;

import com.google.gson.Gson;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.model.HttpResult;
import com.kingja.qiang.util.ToastUtil;
import com.orhanobut.logger.Logger;

import io.reactivex.observers.DefaultObserver;

/**
 * Description：TODO
 * Create Time：2016/10/12 15:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class ResultObserver<T> extends DefaultObserver<HttpResult<T>> {
    private static final String TAG = "ResultObserver";
    private BaseView baseView;

    public ResultObserver(BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    protected void onStart() {
        super.onStart();
        baseView.showLoading();
        RxRe.getInstance().add(baseView, this);
        Log.e(TAG, "onStart: " );
    }

    @Override
    public void onNext(HttpResult<T> httpResult) {

       Logger.json(new Gson().toJson(httpResult));
        baseView.hideLoading();
        if (httpResult.getCode() == 200) {
            onSuccess(httpResult.getData());
        } else {
            ToastUtil.showText(httpResult.getMessage());
        }
    }

    protected abstract void onSuccess(T t);

    @Override
    public void onError(Throwable e) {
        //记录错误
        Log.e(TAG, "onError: "+e.toString() );
        baseView.hideLoading();
    }

    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete: " );
    }

    public void cancleRequest() {
        Log.e(TAG, "cancleRequest: " );
        cancel();
    }
}
