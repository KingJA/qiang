package com.kingja.qiang.page.discount;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.qiang.R;
import com.kingja.qiang.activity.DiscountCenterActivity;
import com.kingja.qiang.adapter.DiscountAdapter;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.callback.EmptyMsgCallback;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.Discount;
import com.kingja.qiang.util.GoUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:我的优惠券
 * Create Time:2018/2/26 10:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DiscountActivity extends BaseTitleActivity implements DiscountContract.View {
    @BindView(R.id.lv_discount)
    ListView lvDiscount;
    @BindView(R.id.ll_discount_center)
    LinearLayout llDiscountCenter;

    @Inject
   DiscountPresenter discountPresenter;
    private DiscountAdapter mDiscountAdapter;
    private List<Discount> discounts = new ArrayList<>();
    private LoadService loadService;

    @OnClick({R.id.ll_discount_center})
    public void onViewClicked(View view) {
        GoUtil.goActivity(this, DiscountCenterActivity.class);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerDiscountCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "我的优惠券";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_discount;
    }

    @Override
    protected void initView() {
        discountPresenter.attachView(this);
        mDiscountAdapter = new DiscountAdapter(this, discounts);
        lvDiscount.setAdapter(mDiscountAdapter);
        loadService = LoadSir.getDefault().register(lvDiscount, (Callback.OnReloadListener) v -> initNet());
    }

    @Override
    protected void initNet() {
        discountPresenter.getDiscount();
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
    public void onGetDiscountSuccess(List<Discount> discounts) {
        if (discounts.size() == 0) {
            loadService.showCallback(EmptyMsgCallback.class);
        } else {
            loadService.showSuccess();
            mDiscountAdapter.setData(discounts);
        }
    }
}
