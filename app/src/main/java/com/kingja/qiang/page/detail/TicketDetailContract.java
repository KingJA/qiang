package com.kingja.qiang.page.detail;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.page.visitor.list.Visitor;

import java.util.List;

import retrofit2.http.Field;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface TicketDetailContract {
    interface View extends BaseView {
        void onGetTicketDetailSuccess(TicketDetail ticketDetail);

        void onGetVisitorsSuccess(List<Visitor> visitors);

        void onSumbitOrderSuccess(String orderId);
    }

    interface Presenter extends BasePresenter<View> {
        void getTicketDetail(String productId);

        void getVisitors(Integer page, Integer pageSize);

        void sumbitOrder(String productId, String touristIds, int quantity, String from);
    }
}
