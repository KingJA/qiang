package com.kingja.qiang.page.order;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface OrderContract {
    interface View extends BaseView {
        void onGetOrdersSuccess(List<Order> orders);
    }

    interface Presenter extends BasePresenter<View> {
        void getOrders(Integer page,Integer pageSize,Integer status);

    }
}
