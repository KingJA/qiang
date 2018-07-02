package com.kingja.qiang.page.login;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.model.entiy.Login;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface LoginContract {
    interface View extends BaseView {
        void onLoginSuccess(Login account);
    }

    interface Presenter extends BasePresenter<View> {
        void login(String mobile, String password, String deviceId, String deviceName,
                   String osName);

    }
}
