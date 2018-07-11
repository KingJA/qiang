package com.kingja.qiang.page.detail;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface TicketDetailContract {
    interface View extends BaseView {
        void onGetTicketDetailSuccess(TicketDetail ticketDetail);
    }

    interface Presenter extends BasePresenter<View> {
        void getTicketDetail(String productId);

    }
}
