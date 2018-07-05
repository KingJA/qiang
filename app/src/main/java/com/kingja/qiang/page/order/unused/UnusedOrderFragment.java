package com.kingja.qiang.page.order.unused;

import android.widget.ListView;

import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.qiang.R;
import com.kingja.qiang.adapter.UnusedOrderAdapter;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.callback.EmptyMsgCallback;
import com.kingja.qiang.constant.Constants;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.order.Order;
import com.kingja.qiang.page.order.OrderContract;
import com.kingja.qiang.page.order.OrderPresenter;
import com.kingja.qiang.page.visitor.list.DaggerVisitorCompnent;

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
public class UnusedOrderFragment extends BaseFragment implements OrderContract.View {
    @BindView(R.id.lv_unused)
    ListView lv_unused;
    private LoadService loadService;

    private List<Order> orders = new ArrayList<>();
    @Inject
    OrderPresenter orderPresenter;
    private UnusedOrderAdapter mUnusedOrderAdapter;

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerUnusedOrderCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initViewAndListener() {
        orderPresenter.attachView(this);
        mUnusedOrderAdapter = new UnusedOrderAdapter(getActivity(), orders);
        lv_unused.setAdapter(mUnusedOrderAdapter);
        loadService = LoadSir.getDefault().register(lv_unused);
    }

    @Override
    protected void initNet() {
        orderPresenter.getOrders(Constants.PAGE_FIRST, Constants.PAGE_SIZE,Constants.ORDER_STATUS_UNUSED);
    }

    @Override
    protected int getContentId() {
        return R.layout.frag_unused;
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
    public void onGetOrdersSuccess(List<Order> orders) {
        if (orders.size() == 0) {
            loadService.showCallback(EmptyMsgCallback.class);
        } else {
            loadService.showSuccess();
            mUnusedOrderAdapter.setData(orders);
        }
    }
}
