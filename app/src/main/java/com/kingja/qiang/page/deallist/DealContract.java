package com.kingja.qiang.page.deallist;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.model.entiy.Deal;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DealContract {
    interface View extends BaseView {
        void onGetDealListSuccess(List<Deal> deals);
    }

    interface Presenter extends BasePresenter<View> {
        void getDealList();

    }
}
