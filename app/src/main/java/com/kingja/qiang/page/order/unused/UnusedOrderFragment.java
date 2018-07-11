package com.kingja.qiang.page.order.unused;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.qiang.R;
import com.kingja.qiang.adapter.UnusedOrderAdapter;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.callback.EmptyMsgCallback;
import com.kingja.qiang.callback.UnLoginCallback;
import com.kingja.qiang.constant.Constants;
import com.kingja.qiang.event.ResetLoginStatusEvent;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.order.Order;
import com.kingja.qiang.page.order.OrderContract;
import com.kingja.qiang.page.order.OrderPresenter;
import com.kingja.qiang.util.LoginChecker;
import com.kingja.qiang.view.RefreshSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description:未使用订单列表
 * Create Time:2018/2/4 15:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UnusedOrderFragment extends BaseFragment implements OrderContract.View, SwipeRefreshLayout
        .OnRefreshListener {
    @BindView(R.id.lv_unused)
    ListView lv_unused;
    @BindView(R.id.srl_unused)
    RefreshSwipeRefreshLayout srl_unused;
    private LoadService loadService;

    private List<Order> orders = new ArrayList<>();
    @Inject
    OrderPresenter orderPresenter;
    private UnusedOrderAdapter mUnusedOrderAdapter;

    @Override
    protected void initVariable() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerUnusedOrderCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    protected void initViewAndListener() {
        orderPresenter.attachView(this);
        srl_unused.setScrollUpChild(lv_unused);
        srl_unused.setOnRefreshListener(this);
        mUnusedOrderAdapter = new UnusedOrderAdapter(getActivity(), orders);
        lv_unused.setAdapter(mUnusedOrderAdapter);
        loadService = LoadSir.getDefault().register(lv_unused);
    }

    @Override
    protected void initNet() {
        if (LoginChecker.isLogin()) {
            orderPresenter.getOrders(Constants.PAGE_FIRST, Constants.PAGE_SIZE, Constants.ORDER_STATUS_UNUSED);
        } else {
            loadService.showCallback(UnLoginCallback.class);
        }
    }

    @Override
    protected int getContentId() {
        return R.layout.frag_unused;
    }

    @Override
    public void showLoading() {
        srl_unused.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srl_unused.setRefreshing(false);
    }

    @Override
    public void onGetOrdersSuccess(List<Order> orders) {
        if (orders.size() == 0) {
            loadService.showCallback(EmptyMsgCallback.class);
        } else {
            loadService.showSuccess();
            mUnusedOrderAdapter.setData(orders);
        }
    }

    @Override
    public void onRefresh() {
        initNet();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resetLoginStatus(ResetLoginStatusEvent resetLoginStatusEvent) {
        initNet();
    }
}
