package com.kingja.qiang.page.detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.imgaeloader.ImageLoader;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.introduce.SceneryIntroduceActivity;
import com.kingja.qiang.view.ChangeNumberView;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Description:景区详情
 * Create Time:2018/7/10 17:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TicketDetailActivity extends BaseTitleActivity implements TicketDetailContract.View, ChangeNumberView.OnChangeNumberListener {

    private String productId;
    @Inject
    TicketDetailPresenter ticketDetailPresenter;

    private ImageView mIvDetailImg;
    private TextView mTvDetailTitle;
    private TextView mTvDetailArea;
    private TextView mTvDetailDate;
    private TextView mTvDetailTime;
    private TextView mTvDetailMethod;
    private TextView mTvDetailMarketPrice;
    private TextView mTvDetailBuyPrice;
    private TextView mTvDetailLimitCount;
    private RecyclerView mRvTicketDetail;
    private TextView mTvDetailBuy;
    private TextView mTvDetailTotalPrice;
    private RelativeLayout mRlTicketIntroduce;
    private TicketDetail ticketDetail;
    private ChangeNumberView mCcvTicketDetail;


    @OnClick({R.id.rl_ticket_introduce, R.id.tv_detail_buy})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.rl_ticket_introduce:
                SceneryIntroduceActivity.goActivity(this, ticketDetail.getScenicid());
                break;
            case R.id.tv_detail_buy:
                SceneryIntroduceActivity.goActivity(this, ticketDetail.getScenicid());
                break;
            default:
                break;
        }

    }

    @Override
    public void initVariable() {
        productId = getIntent().getStringExtra("productId");
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerTicketDetailCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
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
        ticketDetailPresenter.attachView(this);
        mIvDetailImg = findViewById(R.id.iv_detail_img);
        mTvDetailTitle = findViewById(R.id.tv_detail_title);
        mTvDetailArea = findViewById(R.id.tv_detail_area);
        mTvDetailDate = findViewById(R.id.tv_detail_date);
        mTvDetailTime = findViewById(R.id.tv_detail_time);
        mTvDetailMethod = findViewById(R.id.tv_detail_method);
        mTvDetailMarketPrice = findViewById(R.id.tv_detail_marketPrice);
        mTvDetailBuyPrice = findViewById(R.id.tv_detail_buyPrice);
        mTvDetailLimitCount = findViewById(R.id.tv_detail_limitCount);
        mRvTicketDetail = findViewById(R.id.rv_ticket_detail);
        mTvDetailBuy = findViewById(R.id.tv_detail_buy);
        mTvDetailTotalPrice = findViewById(R.id.tv_detail_totalPrice);
        mRlTicketIntroduce = findViewById(R.id.rl_ticket_introduce);
        mCcvTicketDetail = findViewById(R.id.ccv_ticket_detail);
    }

    @Override
    protected void initData() {
        mCcvTicketDetail.setOnChangeNumberListener(this);
    }

    @Override
    protected void initNet() {
        ticketDetailPresenter.getTicketDetail(productId);
    }

    public static void goActivity(Context context, String productId) {
        Intent intent = new Intent(context, TicketDetailActivity.class);
        intent.putExtra("productId", productId);
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
    public void onGetTicketDetailSuccess(TicketDetail ticketDetail) {
        this.ticketDetail = ticketDetail;
        ImageLoader.getInstance().loadImage(this, ticketDetail.getHeadImg(), R.mipmap.ic_placeholder, mIvDetailImg);
        mTvDetailTitle.setText(String.valueOf(ticketDetail.getTicketName()));
        mTvDetailArea.setText(String.valueOf(ticketDetail.getAreaText()));
        mTvDetailDate.setText(String.valueOf(ticketDetail.getVisitDate()));
        mTvDetailTime.setText(String.valueOf(ticketDetail.getVisitTime()));
        mTvDetailMethod.setText(String.valueOf(ticketDetail.getVisitMethod()));
        mTvDetailMarketPrice.setText(String.valueOf(ticketDetail.getMarketPrice()));
        mTvDetailBuyPrice.setText(String.valueOf(ticketDetail.getBuyPrice()));
        mTvDetailLimitCount.setText(String.valueOf(ticketDetail.getBuyLimit()));
        mCcvTicketDetail.setMaxNumber(ticketDetail.getBuyLimit());
        mTvDetailTotalPrice.setText(String.valueOf(ticketDetail.getBuyPrice()));
    }

    @Override
    public void onChangeNumber(int number) {
        mTvDetailTotalPrice.setText(String.valueOf(number*ticketDetail.getBuyPrice()));
    }
}
