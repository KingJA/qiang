package com.kingja.qiang.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.kingja.qiang.R;
import com.kingja.qiang.adapter.OrderPageAdapter;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.injector.component.AppComponent;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/1/22 13:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OrderFragment extends BaseFragment {
    @BindView(R.id.tab_order)
    TabLayout tabOrder;
    @BindView(R.id.vp_content_order)
    ViewPager vpContentOrder;
    private String[] items = {"待使用", "待付款", "已完成"};
    private Fragment mFragmentArr[] = new Fragment[3];


    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViewAndListener() {
        tabOrder.setTabMode(TabLayout.MODE_FIXED);
        tabOrder.addTab(tabOrder.newTab().setText(items[0]));
        tabOrder.addTab(tabOrder.newTab().setText(items[1]));
        tabOrder.addTab(tabOrder.newTab().setText(items[2]));
        mFragmentArr[0] = new UnusedFragment();
        mFragmentArr[1] = new UnusedFragment();
        mFragmentArr[2] = new UnusedFragment();
        OrderPageAdapter mOrderPageAdapter = new OrderPageAdapter(getChildFragmentManager(), mFragmentArr,
                items);
        vpContentOrder.setAdapter(mOrderPageAdapter);
        vpContentOrder.setOffscreenPageLimit(2);
        tabOrder.setupWithViewPager(vpContentOrder);
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.frag_order;
    }

}
