package com.kingja.qiang.page.visitor.edit;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.page.visitor.Visitor;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface VisitorEditContract {
    interface View extends BaseView {
        void onEditVisitorSuccess(Visitor visitor);

    }

    interface Presenter extends BasePresenter<View> {
        void editVisitor(String touristId,String name, String mobile, String idcode);

    }
}
