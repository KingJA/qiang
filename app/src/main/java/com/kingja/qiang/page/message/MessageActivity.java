package com.kingja.qiang.page.message;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kingja.qiang.R;
import com.kingja.qiang.activity.MsgDetailActivity;
import com.kingja.qiang.adapter.MsgAdapter;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.callback.EmptyMsgCallback;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.Message;
import com.kingja.qiang.util.GoUtil;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/2/26 14:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MessageActivity extends BaseTitleActivity implements MessageContract.View {
    @BindView(R.id.lv_msg)
    ListView lvMsg;
    @Inject
    MessagePresenter messagePresenter;
    private MsgAdapter mMsgAdapter;
    private List<Message> messages = new ArrayList<>();
    private LoadService loadService;

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
        return R.layout.activity_mine_msg;
    }

    @Override
    protected void initView() {
        messagePresenter.attachView(this);
        mMsgAdapter = new MsgAdapter(this, messages);
        lvMsg.setAdapter(mMsgAdapter);
        loadService = LoadSir.getDefault().register(lvMsg, (Callback.OnReloadListener) v -> initNet());
    }

    @OnItemClick(R.id.lv_msg)
    public void itemClick(AdapterView<?> parent, View view, int position, long id) {
        GoUtil.goActivity(MessageActivity.this, MsgDetailActivity.class);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {
        messagePresenter.getMessage();
    }

    @Override
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }

    @Override
    public void onGetMessageSuccess(List<Message> messages) {
        if (messages.size() == 0) {
            loadService.showCallback(EmptyMsgCallback.class);
        } else {
            loadService.showSuccess();
            mMsgAdapter.setData(messages);
        }
    }
}
