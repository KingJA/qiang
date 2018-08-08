package com.kingja.qiang.page.sell.selling;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.qiang.R;
import com.kingja.qiang.adapter.SellingAdapter;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.callback.EmptyTicketCallback;
import com.kingja.qiang.callback.TicketCallback;
import com.kingja.qiang.constant.Constants;
import com.kingja.qiang.event.TicketFilterEvent;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.detail.TicketDetailActivity;
import com.kingja.qiang.page.sell.Ticket;
import com.kingja.qiang.page.sell.TicketContract;
import com.kingja.qiang.page.sell.TicketPresenter;
import com.kingja.qiang.util.ToastUtil;
import com.kingja.qiang.view.PullToBottomListView;
import com.kingja.qiang.view.RefreshSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    @BindView(R.id.iv_go_top)
    ImageView iv_go_top;
    private LoadService loadService;
    @Inject
    TicketPresenter ticketPresenter;
    private List<Ticket> tickets = new ArrayList<>();
    private SellingAdapter mSellingAdapter;
    private int page=1;
    private boolean hasMore;

    @OnItemClick(R.id.lv)
    public void itemClick(AdapterView<?> parent, View view, int position, long id) {
        Ticket ticket = (Ticket) parent.getItemAtPosition(position);
        TicketDetailActivity.goActivity(getActivity(),ticket.getId());
    }

    @Override
    protected void initVariable() {
        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
        lv.setGoTop(iv_go_top);
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new TicketCallback())
                .addCallback(new EmptyTicketCallback())
                .setDefaultCallback(TicketCallback.class)
                .build();
                loadService = loadSir.register(lv);
    }

    @Override
    protected void initNet() {
        page = Constants.PAGE_FIRST;
        ticketPresenter.getTickets(areaId, productTypeId, useDates, discountRate, "", page, Constants.PAGE_SIZE, 1);
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
            loadService.showCallback(EmptyTicketCallback.class);
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
            ticketPresenter.getMoreTickets(areaId, productTypeId, useDates, discountRate, "", page, Constants.PAGE_SIZE, 1);
        } else {
            ToastUtil.showText("到底啦");
        }
    }
    private String areaId;
    private String productTypeId;
    private String useDates;
    private String discountRate;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void filterTicket(TicketFilterEvent filterEvent) {
        this.areaId=filterEvent.getAreaId();
        this.productTypeId=filterEvent.getProductTypeId();
        this.useDates=filterEvent.getUseDates();
        this.discountRate=filterEvent.getDiscountRate();
        Log.e(TAG, "areaId: "+areaId+" productTypeId: "+productTypeId +" useDates: "+useDates +" discountRate: "+discountRate );
        ticketPresenter.getTickets(areaId, productTypeId, useDates, discountRate, "", 1, Constants.PAGE_SIZE, 1);
    }
}
