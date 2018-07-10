package com.kingja.qiang.page.detail;

import android.content.Context;
import android.content.Intent;

import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.injector.component.AppComponent;

/**
 * Description:TODO
 * Create Time:2018/7/10 17:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TicketDetailActivity extends BaseTitleActivity {

    private String productId;

    @Override
    public void initVariable() {
        productId = getIntent().getStringExtra("productId");
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "景区详情";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_ticket_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {

    }

    public static void goActivity(Context context, String productId) {
        Intent intent = new Intent(context, TicketDetailActivity.class);
        intent.putExtra("productId", productId);
        context.startActivity(intent);
    }
}
