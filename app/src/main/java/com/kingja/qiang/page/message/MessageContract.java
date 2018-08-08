package com.kingja.qiang.page.message;

import com.kingja.qiang.base.BasePresenter;
import com.kingja.qiang.base.BaseView;
import com.kingja.qiang.model.entiy.Message;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface MessageContract {
    interface View extends BaseView {
        void onGetMessageSuccess(List<Message> messages);
        void onGetMoreMessageSuccess(List<Message> messages);
        void onDeleteMessageSuccess(int position);
        void onReadMessageSuccess(int position);
    }

    interface Presenter extends BasePresenter<View> {
        void getMessage(Integer page, Integer pageSize);
        void getMoreMessage(Integer page, Integer pageSize);

        void deleteMessage(String messageId, Integer flag,int position);
        void readMessage(String messageId, Integer flag,int position);

    }
}
