package com.kingja.qiang.page.register;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface RegisterContract {
    interface View extends BaseView {
        void onRegisterSuccess();

        void onGetCodeSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void register(String mobile, String password, String code);

        void getCode(String mobile, String type);

    }
}
