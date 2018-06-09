package com.kingja.qiang.page.discount;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.model.entiy.Discount;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DiscountContract {
    interface View extends BaseView {
        void onGetDiscountSuccess(List<Discount> discounts);
    }

    interface Presenter extends BasePresenter<View> {
        void getDiscount();

    }
}
