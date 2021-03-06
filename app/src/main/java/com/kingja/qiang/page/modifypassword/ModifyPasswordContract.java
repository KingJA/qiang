package com.kingja.qiang.page.modifypassword;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ModifyPasswordContract {
    interface View extends BaseView {
        void onModifyPasswordSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void modifyPassword(String oldPassword,String newPassword);

    }
}
