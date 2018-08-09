package com.kingja.qiang.page.visitor.add;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.model.entiy.Visitor;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface VisitorAddContract {
    interface View extends BaseView {
        void onAddVisitorSuccess(Visitor visitor);

    }

    interface Presenter extends BasePresenter<View> {
        void addVisitor(String name,String mobile, String idcode);

    }
}
