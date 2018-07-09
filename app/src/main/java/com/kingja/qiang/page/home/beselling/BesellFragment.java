package com.kingja.qiang.page.home.beselling;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.qiang.R;
import com.kingja.qiang.adapter.BesellAdapter;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.callback.EmptyMsgCallback;
import com.kingja.qiang.constant.Constants;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.home.Ticket;
import com.kingja.qiang.page.home.TicketContract;
import com.kingja.qiang.page.home.TicketPresenter;
import com.kingja.qiang.util.ToastUtil;
import com.kingja.qiang.view.PullToBottomListView;
import com.kingja.qiang.view.RefreshSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:待售
 * Create Time:2018/1/22 13:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BesellFragment extends BaseFragment implements TicketContract.View, SwipeRefreshLayout
        .OnRefreshListener, PullToBottomListView.OnScrollToBottom {
    @BindView(R.id.lv)
    PullToBottomListView lv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    private LoadService loadService;
    @Inject
    TicketPresenter ticketPresenter;
    private List<Ticket> tickets = new ArrayList<>();
    private BesellAdapter mBesellAdapter;
    private int page;
    private boolean hasMore;

    @OnItemClick(R.id.lv)
    public void itemClick(AdapterView<?> parent, View view, int position, long id) {
        Ticket ticket = (Ticket) parent.getItemAtPosition(position);
        ToastUtil.showText("敬请期待");
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBesellCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initViewAndListener() {
        ticketPresenter.attachView(this);
        srl.setScrollUpChild(lv);
        srl.setOnRefreshListener(this);
        lv.setOnScrollToBottom(this);
        mBesellAdapter = new BesellAdapter(getActivity(), tickets);
        lv.setAdapter(mBesellAdapter);
        loadService = LoadSir.getDefault().register(lv);
    }

    @Override
    protected void initNet() {
        page = Constants.PAGE_FIRST;
        ticketPresenter.getTickets("", "", "", "", "", page, Constants.PAGE_SIZE, 0);
    }

    @Override
    protected int getContentId() {
        return R.layout.frag_xigo_direct;
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
    public void onGetTicketSuccess(List<Ticket> tickets) {
        hasMore = tickets.size() == Constants.PAGE_SIZE;
        if (tickets.size() == 0) {
            loadService.showCallback(EmptyMsgCallback.class);
        } else {
            loadService.showSuccess();
            mBesellAdapter.setData(tickets);
        }
    }

    @Override
    public void onGetMoreTicketSuccess(List<Ticket> tickets) {
        hasMore = tickets.size() == Constants.PAGE_SIZE;
        mBesellAdapter.addData(tickets);
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
            ticketPresenter.getMoreTickets("", "", "", "", "", page, Constants.PAGE_SIZE, 0);
        } else {
            ToastUtil.showText("到底啦");
        }
    }
}