package com.kingja.qiang.activity;

import android.widget.ListView;

import com.kingja.qiang.R;
import com.kingja.qiang.adapter.ShopcartAdapter;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.callback.EmptyCartCallback;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/3/20 9:29
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ShopCartActivity extends BaseTitleActivity {
    @BindView(R.id.lv_shopcart)
    ListView lvShopcart;
    private ShopcartAdapter mShopcartAdapter;
    private LoadService loadService;

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "购物车(0)";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_shopcart;
    }

    @Override
    protected void initView() {
        mShopcartAdapter = new ShopcartAdapter(this, new ArrayList<String>());
        lvShopcart.setAdapter(mShopcartAdapter);
        loadService = LoadSir.getDefault().register(lvShopcart);
    }

    @Override
    protected void initData() {
        setContentTitle("购物车(" + mShopcartAdapter.getCount() + ")");
        setRightClick("管理", v -> {

        });
    }

    @Override
    protected void initNet() {
        loadService.showCallback(EmptyCartCallback.class);
    }
}
