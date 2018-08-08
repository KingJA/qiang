package com.kingja.qiang.page.mine;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;

import retrofit2.http.Field;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface MineContract {
    interface View extends BaseView {
        void onLogoutSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void logout(String userId, String osName);

    }
}
