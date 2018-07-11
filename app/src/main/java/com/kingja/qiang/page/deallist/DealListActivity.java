package com.kingja.qiang.page.deallist;

import android.widget.ListView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.qiang.R;
import com.kingja.qiang.adapter.DealAdapter;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.callback.EmptyMsgCallback;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.Deal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description:交易明细
 * Create Time:2018/3/10 10:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DealListActivity extends BaseTitleActivity implements DealContract.View {
    @BindView(R.id.lv_deals)
    ListView lvDeals;
    @Inject
    DealPresenter dealPresenter;
    private DealAdapter mDealAdapter;
    private List<Deal> deals = new ArrayList<>();
    private LoadService loadService;

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerDealCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "交易明细";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_deals;
    }

    @Override
    protected void initView() {
        dealPresenter.attachView(this);
        mDealAdapter = new DealAdapter(this, deals);
        lvDeals.setAdapter(mDealAdapter);
        loadService = LoadSir.getDefault().register(lvDeals, (Callback.OnReloadListener) v -> initNet());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {
        dealPresenter.getDealList();
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
    public void onGetDealListSuccess(List<Deal> deals) {
        if (deals.size() == 0) {
            loadService.showCallback(EmptyMsgCallback.class);
        } else {
            loadService.showSuccess();
            mDealAdapter.setData(deals);
        }
    }
}
