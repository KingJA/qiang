package com.kingja.qiang.page.visitor.list;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.page.visitor.Visitor;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface VisitorContract {
    interface View extends BaseView {
        void onGetVisitorsSuccess(List<Visitor> visitors);
        void onDeleteVisitorSuccess(int position);
        void onDefaultVisitorSuccess(int position);

    }

    interface Presenter extends BasePresenter<View> {
        void getVisitors(Integer page, Integer pageSize);
        void deleteVisitor(String touristId,int position);
        void defaultVisitor(String touristId,int position);
    }
}
