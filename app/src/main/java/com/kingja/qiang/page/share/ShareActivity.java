package com.kingja.qiang.page.share;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.LinearLayout;

import com.kingja.qiang.MainActivity;
import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.constant.Constants;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.order.orderdetail.OrderDetailActivity;
import com.kingja.qiang.util.GoUtil;
import com.kingja.qiang.util.Util;
import com.kingja.supershapeview.view.SuperShapeTextView;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/7/16 11:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ShareActivity extends BaseTitleActivity {
    @BindView(R.id.stv_buy_again)
    SuperShapeTextView stvBuyAgain;
    @BindView(R.id.stv_order_detail)
    SuperShapeTextView stvOrderDetail;
    @BindView(R.id.ll_share_weixinfrends)
    LinearLayout llShareWeixinfrends;
    @BindView(R.id.ll_share_weixin)
    LinearLayout llShareWeixin;
    private String orderId;

    private IWXAPI api;
    private static final int THUMB_SIZE = 150;

    private void regToWeixin() {
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID_WEIXIN, true);
        api.registerApp(Constants.APP_ID_WEIXIN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        api.unregisterApp();
    }

    @OnClick({R.id.ll_share_weixinfrends, R.id.ll_share_weixin, R.id.stv_buy_again, R.id.stv_order_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_share_weixinfrends:
                share(SendMessageToWX.Req.WXSceneTimeline);
                break;
            case R.id.ll_share_weixin:
                share(SendMessageToWX.Req.WXSceneSession);
                break;
            case R.id.stv_buy_again:
               GoUtil.goActivityAndFinish(this, MainActivity.class);
                break;
            case R.id.stv_order_detail:
                OrderDetailActivity.goActivity(this,orderId);
                break;
            default:
                break;
        }
    }

    private void share(int shareTo) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.img_share);
        WXImageObject imgObj = new WXImageObject(bmp);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);  // 设置所图；

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;
        req.scene = shareTo;
        api.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    @Override
    public void initVariable() {
        orderId = getIntent().getStringExtra("orderId");
        regToWeixin();
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "支付结果";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_pay_result;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        hideBack();
    }

    @Override
    protected void initNet() {

    }

    public static void goActivity(Context context, String orderId) {
        Intent intent = new Intent(context, ShareActivity.class);
        intent.putExtra("orderId", orderId);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        GoUtil.goActivityAndFinish(this, MainActivity.class);
    }
}
