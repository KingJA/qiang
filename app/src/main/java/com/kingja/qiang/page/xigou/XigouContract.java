package com.kingja.qiang.page.xigou;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.event.ScenicType;
import com.kingja.qiang.model.entiy.City;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface XigouContract {
    interface View extends BaseView {
        void onGetScenicTypeSuccess(List<ScenicType> scenicTypes);
        void onGetCitySuccess(List<City> cities);
    }

    interface Presenter extends BasePresenter<View> {
        void getScenicType(String categoryId);
        void getCity();
    }
}
