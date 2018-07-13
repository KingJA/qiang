package com.kingja.qiang.page.pay;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.constant.Status;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/7/13 16:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PayActivity extends BaseTitleActivity {
    @BindView(R.id.iv_cb_weixinpay)
    ImageView ivCbWeixinpay;
    @BindView(R.id.rl_weixinpay)
    RelativeLayout rlWeixinpay;
    @BindView(R.id.iv_cb_alipay)
    ImageView ivCbAlipay;
    @BindView(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    private String orderId;

    private int payType = Status.PayType.NOPAI;

    @OnClick({R.id.rl_weixinpay, R.id.rl_alipay, R.id.tv_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_weixinpay:
                payType = Status.PayType.WEIXINPAI;
                ivCbAlipay.setBackgroundResource(R.mipmap.bg_cb_circle_nor);
                ivCbWeixinpay.setBackgroundResource(R.mipmap.bg_cb_circle_sel);
                break;
            case R.id.rl_alipay:
                payType = Status.PayType.ALIPAI;
                ivCbAlipay.setBackgroundResource(R.mipmap.bg_cb_circle_sel);
                ivCbWeixinpay.setBackgroundResource(R.mipmap.bg_cb_circle_nor);
                break;
            case R.id.tv_pay:
                pay();
                break;
            default:
                break;
        }
    }

    private void pay() {
        switch (payType) {
            case Status.PayType.NOPAI:
                ToastUtil.showText("请选择支付方式");
                break;
            case Status.PayType.ALIPAI:
                ToastUtil.showText("支付宝");
                break;
            case Status.PayType.WEIXINPAI:
                ToastUtil.showText("微信支付");
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {
        orderId = getIntent().getStringExtra("orderId");
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "收银台";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_pay;
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

    public static void goActivity(Context context, String orderId) {
        Intent intent = new Intent(context, PayActivity.class);
        intent.putExtra("orderId", orderId);
        context.startActivity(intent);
    }

}
