package com.kingja.qiang.page.visitor.edit;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface VisitorEditContract {
    interface View extends BaseView {
        void onEditVisitorSuccess();

    }

    interface Presenter extends BasePresenter<View> {
        void editVisitor(String touristId,String name, String mobile, String idcode);

    }
}
