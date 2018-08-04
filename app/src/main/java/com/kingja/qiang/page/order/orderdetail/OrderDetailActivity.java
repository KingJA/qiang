package com.kingja.qiang.page.order.orderdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.constant.Status;
import com.kingja.qiang.imgaeloader.ImageLoader;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.supershapeview.view.SuperShapeLinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:TODO
 * Create Time:2018/7/5 15:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OrderDetailActivity extends BaseTitleActivity implements OrderDetailContract.View {

    @BindView(R.id.tv_order_title)
    TextView tvOrderTitle;
    @BindView(R.id.tv_order_visitor)
    TextView tvOrderVisitor;
    @BindView(R.id.tv_order_quantity)
    TextView tvOrderQuantity;
    @BindView(R.id.tv_order_paydate)
    TextView tvOrderPaydate;
    @BindView(R.id.tv_order_orderId)
    TextView tvOrderOrderId;
    @BindView(R.id.tv_order_code)
    TextView tvOrderCode;
    @BindView(R.id.wb_order_qcode)
    WebView wbOrderQcode;
    @BindView(R.id.ssll_qcode)
    SuperShapeLinearLayout ssllQcode;
    private String orderId;
    @Inject
    OrderDetailPresenter orderDetailPresenter;

    @Override
    public void initVariable() {
        orderId = getIntent().getStringExtra("orderId");
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerOrderDetailCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "订单详情";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {
        orderDetailPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        wbOrderQcode.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = wbOrderQcode.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
    }

    @Override
    protected void initNet() {
        orderDetailPresenter.getOrderDetail(orderId);
    }

    public static void goActivity(Context context, String orderId) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("orderId", orderId);
        context.startActivity(intent);
    }

    @Override
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }

    @Override
    public void onGetOrderDetailSuccess(OrderDetail orderDetail) {
        tvOrderTitle.setText(orderDetail.getSubject());
        tvOrderVisitor.setText(orderDetail.getTourists());
        tvOrderQuantity.setText(String.valueOf(orderDetail.getQuantity()));
        tvOrderPaydate.setText(orderDetail.getPaidAt());
        tvOrderOrderId.setText(orderDetail.getOrderNo());
        tvOrderCode.setText(orderDetail.getStatus() == Status.OrderStatus.WAIT_USE.getCode() ? "出票中" : orderDetail
                .getTicketcode());
        ssllQcode.setVisibility(orderDetail.getQrcodeurl() == null ? View.GONE : View.VISIBLE);
        wbOrderQcode.loadUrl(orderDetail.getQrcodeurl());
    }
}
