package com.kingja.qiang.page.pay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.constant.Status;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.PayResult;
import com.kingja.qiang.page.share.ShareActivity;
import com.kingja.qiang.util.ToastUtil;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/7/13 16:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PayActivity extends BaseTitleActivity implements PayContract.View {
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

    @Inject
    PayPresenter payPresenter;

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
                payPresenter.alipai(orderId);
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
        DaggerPayCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
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
        payPresenter.attachView(this);
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

    @Override
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }
    private static final int SDK_PAY_FLAG = 1;
    @Override
    public void onAlipaiSuccess(String aliPayResult) {

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> result = alipay.payV2(aliPayResult, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ShareActivity.goActivity(PayActivity.this,orderId);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtil.showText("支付失败");
                        Log.e(TAG, "支付失败: " );
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
}
