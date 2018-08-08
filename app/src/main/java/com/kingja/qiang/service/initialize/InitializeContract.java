package com.kingja.qiang.service.initialize;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.event.ScenicType;
import com.kingja.qiang.model.entiy.City;
import com.kingja.qiang.model.entiy.HotSearch;
import com.kingja.qiang.page.sell.Ticket;

import java.util.List;

import retrofit2.http.Field;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface InitializeContract {
    interface View extends BaseView {
        void onGetHotSearch(List<HotSearch> hotSearches);
        void onGetScenicTypeSuccess(List<ScenicType> scenicTypes);
        void onGetCitySuccess(List<City> cities);
    }

    interface Presenter extends BasePresenter<View> {
        void getHotSearch(int limit);
        void getScenicType(String categoryId);
        void getCity();
    }
}
