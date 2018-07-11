package com.kingja.qiang.page.introduce;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.page.detail.TicketDetail;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface SceneryIntroduceContract {
    interface View extends BaseView {
        void onGetSceneryIntroduceSuccess(SceneryIntroduce sceneryIntroduce);
    }

    interface Presenter extends BasePresenter<View> {
        void getSceneryIntroduce(String scenicId);

    }
}
