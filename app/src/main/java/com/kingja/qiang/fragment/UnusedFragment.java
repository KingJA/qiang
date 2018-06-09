package com.kingja.qiang.fragment;

import android.widget.ListView;

import com.kingja.qiang.R;
import com.kingja.qiang.adapter.UnusedAdapter;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.callback.EmptyOrderCallback;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/2/4 15:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UnusedFragment extends BaseFragment {
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
        UnusedAdapter mUnusedAdapter = new UnusedAdapter(getActivity(), new ArrayList<String>());
        lv_unused.setAdapter(mUnusedAdapter);
        loadService = LoadSir.getDefault().register(lv_unused);
    }

    @Override
    protected void initNet() {
        loadService.showCallback(EmptyOrderCallback.class);
    }

    @Override
    protected int getContentId() {
        return R.layout.frag_unused;
    }
}
