package com.kingja.qiang.activity;

import android.widget.ListView;

import com.kingja.qiang.R;
import com.kingja.qiang.adapter.DiscountCenterAdapter;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.injector.component.AppComponent;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/2/26 10:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DiscountCenterActivity extends BaseTitleActivity {
    @BindView(R.id.lv_discount)
    ListView lvDiscount;

    @Override
    public void initData() {

    }

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "领券中心";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_discount;
    }

    @Override
    protected void initView() {
        DiscountCenterAdapter mDiscountCenterAdapter = new DiscountCenterAdapter(this, new ArrayList<String>());
        lvDiscount.setAdapter(mDiscountCenterAdapter);
    }

    @Override
    protected void initNet() {

    }
}
