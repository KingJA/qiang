package com.kingja.qiang.page.sell;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface TicketContract {
    interface View extends BaseView {
        void onGetTicketSuccess(List<Ticket> tickets);

        void onGetMoreTicketSuccess(List<Ticket> tickets);
    }

    interface Presenter extends BasePresenter<View> {
        void getTickets(String areaId, String productTypeId, String useDates, String discountRate, String keyword,
                        Integer page, Integer pageSize, Integer status);

        void getMoreTickets(String areaId, String productTypeId, String useDates, String discountRate, String keyword,
                            Integer page, Integer pageSize, Integer status);

    }
}
