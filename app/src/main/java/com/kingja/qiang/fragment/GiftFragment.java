package com.kingja.qiang.fragment;

import android.widget.GridView;

import com.kingja.qiang.R;
import com.kingja.qiang.adapter.GiftAdapter;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.injector.component.AppComponent;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/1/22 13:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GiftFragment extends BaseFragment {
    @BindView(R.id.gv_gift)
    GridView gvGift;

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViewAndListener() {
        GiftAdapter mDirectgoAdapter = new GiftAdapter(getActivity(), new ArrayList<String>());
        gvGift.setAdapter(mDirectgoAdapter);
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.frag_gift;
    }
}
