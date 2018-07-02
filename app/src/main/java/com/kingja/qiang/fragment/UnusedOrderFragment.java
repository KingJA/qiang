package com.kingja.qiang.fragment;

import android.widget.ListView;

import com.kingja.qiang.R;
import com.kingja.qiang.adapter.UnusedOrderAdapter;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Description:未使用订单列表
 * Create Time:2018/2/4 15:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UnusedOrderFragment extends BaseFragment {
    @BindView(R.id.lv_unused)
    ListView lv_unused;
    private LoadService loadService;

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViewAndListener() {
        UnusedOrderAdapter mUnusedOrderAdapter = new UnusedOrderAdapter(getActivity(), new ArrayList<String>());
        lv_unused.setAdapter(mUnusedOrderAdapter);
        loadService = LoadSir.getDefault().register(lv_unused);
    }

    @Override
    protected void initNet() {
        loadService.showSuccess();
    }

    @Override
    protected int getContentId() {
        return R.layout.frag_unused;
    }
}
