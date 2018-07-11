package com.kingja.qiang.page.home.selling;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.qiang.R;
import com.kingja.qiang.adapter.SellingAdapter;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.callback.EmptyMsgCallback;
import com.kingja.qiang.callback.TicketCallback;
import com.kingja.qiang.constant.Constants;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.detail.TicketDetail;
import com.kingja.qiang.page.detail.TicketDetailActivity;
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
 * Description:在售
 * Create Time:2018/1/22 13:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SellingFragment extends BaseFragment implements TicketContract.View, SwipeRefreshLayout
        .OnRefreshListener, PullToBottomListView.OnScrollToBottom {
    @BindView(R.id.lv)
    PullToBottomListView lv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    private LoadService loadService;
    @Inject
    TicketPresenter ticketPresenter;
    private List<Ticket> tickets = new ArrayList<>();
    private SellingAdapter mSellingAdapter;
    private int page;
    private boolean hasMore;

    @OnItemClick(R.id.lv)
    public void itemClick(AdapterView<?> parent, View view, int position, long id) {
        Ticket ticket = (Ticket) parent.getItemAtPosition(position);
        if (ticket.isOverDue()) {
            ToastUtil.showText("该产品已到期");
            return;
        }
        if (ticket.isSellOut()) {
            ToastUtil.showText("该产品已售罄");
            return;
        }

        TicketDetailActivity.goActivity(getActivity(),ticket.getId());
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerSellingCompnent.builder()
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
        mSellingAdapter = new SellingAdapter(getActivity(), tickets);
        lv.setAdapter(mSellingAdapter);
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new TicketCallback())
                .addCallback(new EmptyMsgCallback())
                .setDefaultCallback(TicketCallback.class)
                .build();
                loadService = loadSir.register(lv);
    }

    @Override
    protected void initNet() {
        page = Constants.PAGE_FIRST;
        ticketPresenter.getTickets("", "", "", "", "", page, Constants.PAGE_SIZE, 1);
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
            mSellingAdapter.setData(tickets);
        }
    }

    @Override
    public void onGetMoreTicketSuccess(List<Ticket> tickets) {
        hasMore = tickets.size() == Constants.PAGE_SIZE;
        mSellingAdapter.addData(tickets);
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
            ticketPresenter.getMoreTickets("", "", "", "", "", page, Constants.PAGE_SIZE, 1);
        } else {
            ToastUtil.showText("到底啦");
        }
    }
}
