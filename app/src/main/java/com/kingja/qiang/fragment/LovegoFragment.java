package com.kingja.qiang.fragment;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.kingja.qiang.R;
import com.kingja.qiang.adapter.LovegoPageAdapter;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.injector.component.AppComponent;

import java.lang.reflect.Field;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/1/22 13:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LovegoFragment extends BaseFragment {
    @BindView(R.id.tab_lovego)
    TabLayout tabLovego;
    @BindView(R.id.vp_content_lovego)
    ViewPager vpContentLovego;
    private String[] items = {"精选", "送情侣", "送情侣", "送长辈", "商务送"};
    private Fragment mFragmentArr[] = new Fragment[5];


    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViewAndListener() {
        tabLovego.setTabMode(TabLayout.MODE_FIXED);
        tabLovego.addTab(tabLovego.newTab().setText(items[0]));
        tabLovego.addTab(tabLovego.newTab().setText(items[1]));
        tabLovego.addTab(tabLovego.newTab().setText(items[2]));
        tabLovego.addTab(tabLovego.newTab().setText(items[3]));
        tabLovego.addTab(tabLovego.newTab().setText(items[4]));
//        tabLovego.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(tabLovego, 60, 60);
//            }
//        });

        mFragmentArr[0] = new GiftFragment();
        mFragmentArr[1] = new GiftFragment();
        mFragmentArr[2] = new GiftFragment();
        mFragmentArr[3] = new GiftFragment();
        mFragmentArr[4] = new GiftFragment();
        LovegoPageAdapter mLovegoPageAdapter = new LovegoPageAdapter(getChildFragmentManager(), mFragmentArr,
                items);
        vpContentLovego.setAdapter(mLovegoPageAdapter);
        vpContentLovego.setOffscreenPageLimit(2);
        tabLovego.setupWithViewPager(vpContentLovego);
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.frag_lovego;
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }
}
