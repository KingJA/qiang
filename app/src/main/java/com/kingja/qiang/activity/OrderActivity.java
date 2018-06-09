package com.kingja.qiang.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.kingja.qiang.R;
import com.kingja.qiang.adapter.OrderPageAdapter;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.fragment.UnusedFragment;
import com.kingja.qiang.injector.component.AppComponent;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/2/26 14:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OrderActivity extends BaseTitleActivity {
    @BindView(R.id.tab_order)
    TabLayout tabOrder;
    @BindView(R.id.vp_content_order)
    ViewPager vpContentOrder;
    private String[] items = {"全部订单", "待使用", "待付款", "已完成"};
    private Fragment mFragmentArr[] = new Fragment[4];

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "我的订单";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_order;
    }

    @Override
    protected void initView() {
        tabOrder.setTabMode(TabLayout.MODE_FIXED);
        tabOrder.addTab(tabOrder.newTab().setText(items[0]));
        tabOrder.addTab(tabOrder.newTab().setText(items[1]));
        tabOrder.addTab(tabOrder.newTab().setText(items[2]));
        tabOrder.addTab(tabOrder.newTab().setText(items[3]));
        mFragmentArr[0] = new UnusedFragment();
        mFragmentArr[1] = new UnusedFragment();
        mFragmentArr[2] = new UnusedFragment();
        mFragmentArr[3] = new UnusedFragment();
        OrderPageAdapter mOrderPageAdapter = new OrderPageAdapter(getSupportFragmentManager(), mFragmentArr,
                items);
        vpContentOrder.setAdapter(mOrderPageAdapter);
        vpContentOrder.setOffscreenPageLimit(2);
        tabOrder.setupWithViewPager(vpContentOrder);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {

    }


}
