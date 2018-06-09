package com.kingja.qiang.page.setting;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface SettingContract {
    interface View extends BaseView {
        void onLogoutSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void logout();

    }
}
