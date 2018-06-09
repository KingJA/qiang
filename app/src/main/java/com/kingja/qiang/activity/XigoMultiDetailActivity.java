package com.kingja.qiang.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.kingja.qiang.R;
import com.kingja.qiang.adapter.BaseRvAdaper;
import com.kingja.qiang.adapter.GallerAdapter;
import com.kingja.qiang.adapter.TicketPriceAdapter;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.Item;
import com.kingja.qiang.ui.FixedListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:嬉逛-分集详情
 * Create Time:2018/2/2 16:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class XigoMultiDetailActivity extends BaseTitleActivity {
    @BindView(R.id.iv_shopCart)
    ImageView ivShopCart;
    @BindView(R.id.rv_xigo_multi_detail)
    RecyclerView rvXigoMultiDetail;
    @BindView(R.id.tab_date)
    TabLayout tabDate;
    @BindView(R.id.lv_ticket_price)
    FixedListView lvTicketPrice;
    private List<Item> items;
    private int currentPosition;
    private String[] tabItems = {"2月1号", "2月2号", "2月3号", "2月4号", "2月5号", "2月6号"};

    @Override
    public void initData() {

    }

    @Override
    public void initVariable() {
        items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(new Item(R.mipmap.direct_1, "item" + i));
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "演出详情";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_xigo_multi_detail;
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        rvXigoMultiDetail.setLayoutManager(layoutManager);
        final GallerAdapter gallerAdapter = new GallerAdapter(this, items);
        gallerAdapter.setOnItemClickListener(new BaseRvAdaper.OnItemClickListener() {
            @Override
            public void onItemClick(BaseRvAdaper<?> baseRvAdaper, View itemView, int position) {
                if (position >= currentPosition) {
                    rvXigoMultiDetail.smoothScrollToPosition(position + 1);
                } else {
                    if (position > 0) {
                        rvXigoMultiDetail.smoothScrollToPosition(position - 1);
                    }
                }
                currentPosition = position;
                gallerAdapter.selectPosition(position);
            }
        });
        rvXigoMultiDetail.setAdapter(gallerAdapter);
        rvXigoMultiDetail.setItemAnimator(new DefaultItemAnimator());
        rvXigoMultiDetail.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        new LinearSnapHelper().attachToRecyclerView(rvXigoMultiDetail);

        tabDate.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < tabItems.length; i++) {
            tabDate.addTab(tabDate.newTab().setText(tabItems[i]));
        }
        TicketPriceAdapter mTicketPriceAdapter = new TicketPriceAdapter(XigoMultiDetailActivity.this,
                getRandomList());
        lvTicketPrice.setAdapter(mTicketPriceAdapter);
        tabDate.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TicketPriceAdapter mTicketPriceAdapter = new TicketPriceAdapter(XigoMultiDetailActivity.this,
                        getRandomList());
                lvTicketPrice.setAdapter(mTicketPriceAdapter);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public List<String> getRandomList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(6) + 3; i++) {
            list.add("");
        }
        return list;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
