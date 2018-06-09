package com.kingja.qiang.page.wallet;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.model.entiy.Wallet;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface WalletContract {
    interface View extends BaseView {
        void onGetWalletSuccess(Wallet wallet);
    }

    interface Presenter extends BasePresenter<View> {
        void getWallet();

    }
}
