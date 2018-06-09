package com.kingja.qiang.page.mine_friends;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.model.entiy.Friend;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface MineFriendsContract {
    interface View extends BaseView {
        void onGetMineFriendsSuccess(List<Friend> friends);
    }

    interface Presenter extends BasePresenter<View> {
        void getMineFriends();

    }
}
