package com.kingja.qiang.page.visitor;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;

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

    }

    interface Presenter extends BasePresenter<View> {
        void getVisitors(Integer page, Integer pageSize);

    }
}
