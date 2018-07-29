package com.kingja.qiang.page.visitor.list;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.qiang.R;
import com.kingja.qiang.activity.MsgDetailActivity;
import com.kingja.qiang.adapter.VisitorAdapter;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.callback.EmptyMsgCallback;
import com.kingja.qiang.callback.EmptyVisitorCallback;
import com.kingja.qiang.constant.Constants;
import com.kingja.qiang.event.AddVisitorEvent;
import com.kingja.qiang.event.RefreshVisitorsEvent;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.visitor.Visitor;
import com.kingja.qiang.page.visitor.add.VisitorAddActivity;
import com.kingja.qiang.page.visitor.edit.VisitorEditActivity;
import com.kingja.qiang.util.DialogUtil;
import com.kingja.qiang.util.GoUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
public class VisitorListActivity extends BaseTitleActivity implements VisitorContract.View, VisitorAdapter
        .OnVistorOperListener {
    @BindView(R.id.lv_msg)
    ListView lvMsg;
    @Inject
    VisitorPresenter visitorPresenter;
    private VisitorAdapter mVisitorAdapter;
    private List<Visitor> visitors = new ArrayList<>();
    private LoadService loadService;
    private boolean fromTitketDetail;

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        fromTitketDetail = getIntent().getBooleanExtra("fromTitketDetail", false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
        return R.layout.activity_mine_visitor;
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


    }

    @Override
    protected void initData() {
        setRightClick("新增游客", v -> {
            GoUtil.goActivity(VisitorListActivity.this, VisitorAddActivity.class);
        });
        mVisitorAdapter.setOnVistorOperListener(this);
    }

    @Override
    protected void initNet() {
        visitorPresenter.getVisitors(1, Constants.PAGE_SIZE_100);
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

    @Override
    public void onDeleteVisitorSuccess(int position) {
        mVisitorAdapter.removeItem(position);
        if (mVisitorAdapter.getCount() == 0) {
            loadService.showCallback(EmptyVisitorCallback.class);
        }

    }

    @Override
    public void onDefaultVisitorSuccess(int position) {
        mVisitorAdapter.setDefault(position);
    }

    @Subscribe
    public void refreshVisitors(RefreshVisitorsEvent refreshVisitorsEvent) {
        visitorPresenter.getVisitors(1, Constants.PAGE_SIZE_100);
    }

    @Override
    public void onDeleteVisitor(String touristId, int position) {
        DialogUtil.showDoubleDialog(this, "是否删除该游客信息?", new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                visitorPresenter.deleteVisitor(touristId, position);
            }
        });

    }

    @Override
    public void onDefaultVisitor(String touristId, int position) {
        visitorPresenter.defaultVisitor(touristId, position);
    }

    @Override
    public void onEditVisitor(Visitor visitor) {
        VisitorEditActivity.goActivity(this, visitor);
    }

    @Override
    public void onSelectVisitor(Visitor visitor) {
        if (fromTitketDetail) {
            EventBus.getDefault().post(new AddVisitorEvent(visitor));
            finish();
        }
    }
}
