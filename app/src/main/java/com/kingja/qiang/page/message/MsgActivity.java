package com.kingja.qiang.page.message;

import android.support.v4.widget.SwipeRefreshLayout;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.qiang.R;
import com.kingja.qiang.activity.MsgDetailActivity;
import com.kingja.qiang.adapter.MsgAdapter;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.callback.EmptyMsgCallback;
import com.kingja.qiang.constant.Constants;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.Message;
import com.kingja.qiang.util.SpSir;
import com.kingja.qiang.util.ToastUtil;
import com.kingja.qiang.view.PullToBottomListView;
import com.kingja.qiang.view.RefreshSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description:消息列表
 * Create Time:2018/2/26 14:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MsgActivity extends BaseTitleActivity implements MessageContract.View, MsgAdapter.OnMsgOperListener,
        SwipeRefreshLayout.OnRefreshListener, PullToBottomListView.OnScrollToBottom {
    @BindView(R.id.lv)
    PullToBottomListView lv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    @Inject
    MessagePresenter messagePresenter;
    private MsgAdapter mMsgAdapter;
    private List<Message> messages = new ArrayList<>();
    private LoadService loadService;

    public static final Integer MSG_OPER_READ = 1;
    public static final Integer MSG_OPER_DELETE = 2;
    private int page;
    private boolean hasMore;

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerMessageCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "我的消息";
    }

    @Override
    protected int getContentView() {
        return R.layout.layout_lv_simple;
    }

    @Override
    protected void initView() {
        messagePresenter.attachView(this);
        srl.setScrollUpChild(lv);
        mMsgAdapter = new MsgAdapter(this, messages);
        lv.setAdapter(mMsgAdapter);
        loadService = LoadSir.getDefault().register(lv, (Callback.OnReloadListener) v -> initNet());
    }

    @Override
    protected void initData() {
        SpSir.getInstance().clearMsgCount();
        mMsgAdapter.setOnVistorOperListener(this);
        srl.setOnRefreshListener(this);
        lv.setOnScrollToBottom(this);
    }

    @Override
    protected void initNet() {
        page = Constants.PAGE_FIRST;
        messagePresenter.getMessage(page, Constants.PAGE_SIZE);
    }

    @Override
    public void showLoading() {
        srl.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srl.setRefreshing(false);
    }

    @Override
    public void onGetMessageSuccess(List<Message> messages) {
        hasMore = messages.size() == Constants.PAGE_SIZE;
        if (messages.size() == 0) {
            loadService.showCallback(EmptyMsgCallback.class);
        } else {
            loadService.showSuccess();
            mMsgAdapter.setData(messages);
        }
    }

    @Override
    public void onGetMoreMessageSuccess(List<Message> messages) {
        hasMore = messages.size() == Constants.PAGE_SIZE;
        mMsgAdapter.addData(messages);
    }

    @Override
    public void onDeleteMessageSuccess(int position) {
        mMsgAdapter.removeItem(position);
    }

    @Override
    public void onReadMessageSuccess(int position) {
        mMsgAdapter.setRead(position);
    }


    @Override
    public void onDeleteMsg(String messageId, int position) {
        messagePresenter.deleteMessage(messageId, MSG_OPER_DELETE, position);
    }

    @Override
    public void onReadMsg(Message message, int position) {
        messagePresenter.readMessage(message.getId(), MSG_OPER_READ, position);
        MsgDetailActivity.goActivity(this, message);
    }

    @Override
    public void onRefresh() {
        initNet();
    }

    @Override
    public void onScrollToBottom() {
        if (srl.isRefreshing()) {
            return;
        }
        if (hasMore) {
            page++;
            messagePresenter.getMoreMessage(page, Constants.PAGE_SIZE);
        } else {
            ToastUtil.showText("到底啦");
        }
    }
}
