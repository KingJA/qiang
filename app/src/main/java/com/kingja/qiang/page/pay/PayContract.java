package com.kingja.qiang.page.pay;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.model.entiy.AliPayResult;
import com.kingja.qiang.model.entiy.WeixinPayResult;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface PayContract {
    interface View extends BaseView {
        void onAlipaiSuccess(String aliPayResult);
        void onWeixinpaiSuccess(WeixinPayResult weixinPayResult);
    }

    interface Presenter extends BasePresenter<View> {
        void alipai(String orderId);
        void weixinpai(String orderId);

    }
}
