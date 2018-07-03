package com.kingja.qiang.page.visitor;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.qiang.R;
import com.kingja.qiang.activity.MsgDetailActivity;
import com.kingja.qiang.adapter.VisitorAdapter;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.callback.EmptyMsgCallback;
import com.kingja.qiang.constant.Constants;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.util.GoUtil;
import com.kingja.qiang.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:游客列表
 * Create Time:2018/2/26 14:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class VisitorActivity extends BaseTitleActivity implements VisitorContract.View {
    @BindView(R.id.lv_msg)
    ListView lvMsg;
    @Inject
   VisitorPresenter visitorPresenter;
    private VisitorAdapter mVisitorAdapter;
    private List<Visitor> visitors = new ArrayList<>();
    private LoadService loadService;

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerVisitorCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "游客列表";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_msg;
    }

    @Override
    protected void initView() {
        visitorPresenter.attachView(this);
        mVisitorAdapter = new VisitorAdapter(this, visitors);
        lvMsg.setAdapter(mVisitorAdapter);
        loadService = LoadSir.getDefault().register(lvMsg, (Callback.OnReloadListener) v -> initNet());
    }

    @OnItemClick(R.id.lv_msg)
    public void itemClick(AdapterView<?> parent, View view, int position, long id) {
        GoUtil.goActivity(VisitorActivity.this, MsgDetailActivity.class);
    }

    @Override
    protected void initData() {
        setRightClick("新增游客", v -> {
            ToastUtil.showText("新增游客信息");

        });

    }

    @Override
    protected void initNet() {
        visitorPresenter.getVisitors(1, Constants.PAGE_SIZE);
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
    public void onGetVisitorsSuccess(List<Visitor> visitors) {
        if (visitors.size() == 0) {
            loadService.showCallback(EmptyMsgCallback.class);
        } else {
            loadService.showSuccess();
            mVisitorAdapter.setData(visitors);
        }
    }
}
