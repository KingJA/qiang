package com.kingja.qiang.page.modify_nickname;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ModifyNicknameContract {
    interface View extends BaseView {
        void onModifyNicknameSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void modifyNickname(String nickname);

    }
}
