package com.kingja.qiang.service.initialize;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.model.entiy.HotSearch;
import com.kingja.qiang.page.home.Ticket;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface InitializeContract {
    interface View extends BaseView {
        void onGetHotSearch(List<HotSearch> hotSearches);
    }

    interface Presenter extends BasePresenter<View> {
        void getHotSearch(int limit);

    }
}
