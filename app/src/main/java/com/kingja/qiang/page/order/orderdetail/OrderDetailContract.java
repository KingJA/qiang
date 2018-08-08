package com.kingja.qiang.page.order.orderdetail;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface OrderDetailContract {
    interface View extends BaseView {
        void onGetOrderDetailSuccess(OrderDetail orderDetail);
    }

    interface Presenter extends BasePresenter<View> {
        void getOrderDetail(String orderId);

    }
}
