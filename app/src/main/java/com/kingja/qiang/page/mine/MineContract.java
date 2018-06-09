package com.kingja.qiang.page.mine;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.model.entiy.PersonalInfo;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface MineContract {
    interface View extends BaseView {
        void onGetPersonalInfoSuccess(PersonalInfo personalInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getPersonalInfo(String uid);

    }
}
