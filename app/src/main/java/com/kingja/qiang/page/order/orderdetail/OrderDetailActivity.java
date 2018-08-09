package com.kingja.qiang.page.order.orderdetail;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.adapter.QcodePagerAdapter;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.constant.Status;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.OrderDetail;
import com.kingja.qiang.util.AppUtil;
import com.kingja.supershapeview.view.SuperShapeRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

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
    @BindView(R.id.vp_order)
    ViewPager vpOrder;
    @BindView(R.id.ssrl_qcode)
    SuperShapeRelativeLayout ssrlQcode;
    @BindView(R.id.ll_pointContainer)
    LinearLayout llPointContainer;
    private String orderId;
    private List<View> points = new ArrayList<>();
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
        String qrcodeurl = orderDetail.getQrcodeurl();
        ssrlQcode.setVisibility(TextUtils.isEmpty(qrcodeurl) ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(qrcodeurl)) {
            String[] qcodes = qrcodeurl.split(",");
            initDot(qcodes);
            vpOrder.setAdapter(new QcodePagerAdapter(this, qcodes));
            vpOrder.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (qcodes.length < 2) {
                        return;
                    }
                    for (int i = 0; i < points.size(); i++) {
                        if (i == position) {
                            points.get(i).setBackgroundResource(R.mipmap.ic_dot_action);
                        } else {
                            points.get(i).setBackgroundResource(R.mipmap.ic_dot_nor);
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    private void initDot(String[] qcodes) {
        if (qcodes.length < 2) {
            return;
        }
        for (int i = 0; i < qcodes.length; i++) {
            View view = new View(this);
            if (i == 0) {
                view.setBackgroundResource(R.mipmap.ic_dot_action);
            } else {
                view.setBackgroundResource(R.mipmap.ic_dot_nor);
            }
            points.add(view);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(AppUtil.dp2px(10), AppUtil.dp2px(10));
        layoutParams.setMargins(0, 0, AppUtil.dp2px(10), 0);
        for (int i = 0; i < qcodes.length; i++) {
            llPointContainer.addView(points.get(i), layoutParams);
        }

    }
}