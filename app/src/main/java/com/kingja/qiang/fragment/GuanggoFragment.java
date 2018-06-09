package com.kingja.qiang.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.kingja.qiang.R;
import com.kingja.qiang.adapter.HigoPageAdapter;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.injector.component.AppComponent;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/1/22 13:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GuanggoFragment extends BaseFragment {
    @BindView(R.id.tab_higo)
    TabLayout tabHigo;
    @BindView(R.id.vp_content_higo)
    ViewPager vpContentHigo;
    private Fragment mFragmentArr[] = new Fragment[2];
    private String[] items = {"景区购", "酒店购"};
    private int[] icons = {R.mipmap.ic_scenic, R.mipmap.ic_hotel};

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViewAndListener() {
        tabHigo.setTabMode(TabLayout.MODE_FIXED);
        tabHigo.addTab(tabHigo.newTab().setText(items[0]));
        tabHigo.addTab(tabHigo.newTab().setText(items[1]));

        mFragmentArr[0] = new ScenicFragment();
        mFragmentArr[1] = new HotelFragment();
        HigoPageAdapter mHigoPageAdapter = new HigoPageAdapter(getActivity(), getFragmentManager(), mFragmentArr,
                items, icons);
        vpContentHigo.setAdapter(mHigoPageAdapter);
        vpContentHigo.setOffscreenPageLimit(2);
        tabHigo.setupWithViewPager(vpContentHigo);

        for (int i = 0; i < tabHigo.getTabCount(); i++) {
            TabLayout.Tab tab = tabHigo.getTabAt(i);
            tab.setCustomView(mHigoPageAdapter.getTabView(i));
        }

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.frag_guanggo;
    }


}
