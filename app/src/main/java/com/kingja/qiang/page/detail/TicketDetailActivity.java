package com.kingja.qiang.page.detail;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kingja.qiang.R;
import com.kingja.qiang.adapter.BaseRvAdaper;
import com.kingja.qiang.adapter.VisitorTabAdapter;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.constant.Constants;
import com.kingja.qiang.constant.Status;
import com.kingja.qiang.event.AddVisitorEvent;
import com.kingja.qiang.event.PrfectVisitorEvent;
import com.kingja.qiang.event.ResetLoginStatusEvent;
import com.kingja.qiang.imgaeloader.ImageLoader;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.OrderResult;
import com.kingja.qiang.model.entiy.TicketDetail;
import com.kingja.qiang.page.introduce.SceneryIntroduceActivity;
import com.kingja.qiang.page.pay.PayActivity;
import com.kingja.qiang.model.entiy.Visitor;
import com.kingja.qiang.page.visitor.list.VisitorListActivity;
import com.kingja.qiang.page.visitor.prefect.VisitorPrefectActivity;
import com.kingja.qiang.util.DateUtil;
import com.kingja.qiang.util.DialogUtil;
import com.kingja.qiang.util.LoginChecker;
import com.kingja.qiang.util.ToastUtil;
import com.kingja.qiang.view.ChangeNumberView;
import com.kingja.qiang.view.RvItemDecoration;
import com.kingja.supershapeview.view.SuperShapeLinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Description:景区详情
 * Create Time:2018/7/10 17:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TicketDetailActivity extends BaseTitleActivity implements TicketDetailContract.View, ChangeNumberView
        .OnChangeNumberListener, BaseRvAdaper.OnItemClickListener<Visitor> {

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
    private TextView mTvVisitorName;
    private TextView mTvVisitorPhone;
    private TextView mTvisitorIdcode;
    private RelativeLayout mRlTicketIntroduce;
    private TicketDetail ticketDetail;
    private ChangeNumberView mCcvTicketDetail;
    private SuperShapeLinearLayout mSsllVisitorInfo;
    private List<Visitor> visitors = new ArrayList<>();
    private VisitorTabAdapter visitorTabAdapter;
    private String touristId;
    private int idcodeNeed;
    private int status;
    private String startTime;
    private String endTime;
    private Timer timer;
    private TimerTask timerTask;
    private TextView mTvDetailRemark;
    private TextView mTvSellCount;
    private TextView mTvTotalCount;

    @OnClick({R.id.rl_ticket_introduce, R.id.tv_detail_buy})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.rl_ticket_introduce:
                SceneryIntroduceActivity.goActivity(this, ticketDetail.getScenicid());
                break;
            case R.id.tv_detail_buy:
                if (LoginChecker.isLogin()) {
                    checkBuyInfo();
                } else {
                    DialogUtil.showLoginActivity(this);
                }
                break;
            default:
                break;
        }
    }

    private void checkBuyInfo() {
        String idcode = mTvisitorIdcode.getText().toString().trim();
        if (TextUtils.isEmpty(touristId)) {
            if (visitorTabAdapter.getItemCount() == 1) {
                ToastUtil.showText("没有游客信息，请新增一位游客");
            } else {
                ToastUtil.showText("请选择一位出行游客信息");
            }
        } else if (idcodeNeed == 1 && TextUtils.isEmpty(idcode)) {
            showPrefectDialog();
        } else {
            checkBuyable();
        }
    }

    private void checkBuyable() {
        switch (status) {
            case Status.SellStatus.UNSELLING:
                if (DateUtil.isBeginSell(startTime)) {
                    sumbmitOrder();
                } else {
                    ToastUtil.showText("暂未开售");
                }
                break;
            case Status.SellStatus.SELLOUT:
                ToastUtil.showText("抢购结束");
                break;
            case Status.SellStatus.SELLING:
                sumbmitOrder();
                break;
            default:
                break;
        }
    }


    private void sumbmitOrder() {
        ticketDetailPresenter.sumbitOrder(productId, touristId, mCcvTicketDetail.getNumber(), Constants
                .PLATFORM_ANDROID);

    }

    private void showPrefectDialog() {
        DialogUtil.showDoubleDialog(this, "该景区需要提供身份证号码，是否去完善", new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                String name = mTvVisitorName.getText().toString().trim();
                String phone = mTvVisitorPhone.getText().toString().trim();
                VisitorPrefectActivity.goActivity(TicketDetailActivity.this, new Visitor(touristId, name, phone));
            }
        });
    }

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        productId = getIntent().getStringExtra("productId");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (timer != null) {
            timer.cancel();
        }
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
        mTvVisitorName = findViewById(R.id.tv_visitor_name);
        mTvSellCount = findViewById(R.id.tv_sellCount);
        mTvTotalCount = findViewById(R.id.tv_totalCount);
        mTvDetailRemark = findViewById(R.id.tv_detail_remark);
        mTvVisitorPhone = findViewById(R.id.tv_visitor_phone);
        mTvisitorIdcode = findViewById(R.id.tv_visitor_idcode);
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
        mSsllVisitorInfo = findViewById(R.id.ssll_visitor_info);
        visitorTabAdapter = new VisitorTabAdapter(this, visitors);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        mRvTicketDetail.setLayoutManager(layoutManager);
        mRvTicketDetail.setAdapter(visitorTabAdapter);
        mRvTicketDetail.setItemAnimator(new DefaultItemAnimator());
        mRvTicketDetail.addItemDecoration(new RvItemDecoration(this, RvItemDecoration.LayoutStyle.HORIZONTAL_LIST,
                12, 0x00ffffff));
    }

    @Override
    protected void initData() {
        mCcvTicketDetail.setOnChangeNumberListener(this);
        visitorTabAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initNet() {
        ticketDetailPresenter.getTicketDetail(productId);
        ticketDetailPresenter.getVisitors(Constants.PAGE_FIRST, Constants.PAGE_SIZE_100);
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
        mTvSellCount.setText(String.valueOf(ticketDetail.getSellCount()));
        mTvTotalCount.setText(String.valueOf(ticketDetail.getTotalCount()));
        mTvDetailTitle.setText(String.valueOf(ticketDetail.getTicketName()));
        mTvDetailArea.setText(String.valueOf(ticketDetail.getAreaText()));
        mTvDetailDate.setText(String.valueOf(ticketDetail.getVisitDate()));
        mTvDetailTime.setText(String.valueOf(ticketDetail.getVisitTime()));
        mTvDetailMethod.setText(String.valueOf(ticketDetail.getVisitMethod()));
        mTvDetailMarketPrice.setText(String.valueOf((int) ticketDetail.getMarketPrice()));
        mTvDetailBuyPrice.setText(String.valueOf((int) ticketDetail.getBuyPrice()));
        mTvDetailLimitCount.setText(String.valueOf(ticketDetail.getBuyLimit()));
        mCcvTicketDetail.setMaxNumber(ticketDetail.getBuyLimit());
        mTvDetailTotalPrice.setText(String.valueOf((int) ticketDetail.getBuyPrice()));
        mTvDetailRemark.setText(ticketDetail.getRemarks());
        idcodeNeed = ticketDetail.getIdcodeNeed();
        status = ticketDetail.getStatus();
        startTime = ticketDetail.getStartTime();
        endTime = ticketDetail.getEndTime();
        setTicketStatus();
        initTimer();
    }

    private void initTimer() {
        if (status == Status.SellStatus.UNSELLING) {
            //待售
            startTimer(new TimerTask() {
                @Override
                public void run() {
                    Log.e(TAG, "判断是否开始: ");
                    if (DateUtil.isBeginSell(startTime)) {
                        mTvDetailBuy.setBackgroundColor(ContextCompat.getColor(TicketDetailActivity.this, R.color
                                .red_hi));
                        mTvDetailBuy.setText("立即抢购");
                        mTvDetailBuy.setEnabled(true);
                        timer.cancel();
                    }

                }
            });
        } else if (status == Status.SellStatus.SELLING) {
            //在售
            startTimer(new TimerTask() {
                @Override
                public void run() {
                    Log.e(TAG, "判断是否结束: ");
                    if (DateUtil.isOverDue(endTime)) {
                        mTvDetailBuy.setBackgroundColor(ContextCompat.getColor(TicketDetailActivity.this, R.color
                                .gray_hi));
                        mTvDetailBuy.setText("抢购结束");
                        mTvDetailBuy.setEnabled(false);
                        timer.cancel();
                    }

                }
            });
        }
    }

    private void startTimer(TimerTask timerTask) {
        timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }


    @Override
    public void onGetVisitorsSuccess(List<Visitor> visitors) {
        if (visitors.size() == 0) {
            mSsllVisitorInfo.setVisibility(View.GONE);
        } else if (hasDefault(visitors)) {
            mSsllVisitorInfo.setVisibility(View.VISIBLE);
            fillVisitorInfo(visitors.get(0));
        } else {
            mSsllVisitorInfo.setVisibility(View.GONE);
        }
        visitorTabAdapter.setData(getSelectVisitor(visitors));
    }

    @Override
    public void onSumbitOrderSuccess(OrderResult orderResult) {
        PayActivity.goActivity(this, orderResult.getOrderId());
    }

    @Override
    public void onLoginFail() {
        mSsllVisitorInfo.setVisibility(View.GONE);
        visitorTabAdapter.setData(getSelectVisitor(visitors));
    }

    private boolean hasDefault(List<Visitor> visitors) {
        for (Visitor visitor : visitors) {
            if (visitor.getIsdefault() == 1) {
                return true;
            }
        }
        return false;
    }

    private List<Visitor> getSelectVisitor(List<Visitor> visitors) {
        if (visitors.size() > 3) {
            visitors = visitors.subList(0, 3);
        }
        Visitor addVisitor = new Visitor();
        addVisitor.setName("新增/更换 >");
        visitors.add(addVisitor);
        for (Visitor visitor : visitors) {
            if (visitor.getIsdefault() == 1) {
                visitor.setSelected(true);
            }
        }
        return visitors;
    }

    @Override
    public void onChangeNumber(int number) {
        mTvDetailTotalPrice.setText(String.valueOf(number * (int) ticketDetail.getBuyPrice()));
    }

    @Override
    public void onItemClick(Visitor visitor, int position) {
        if (position == visitorTabAdapter.getItemCount() - 1) {
            Intent intent = new Intent(this, VisitorListActivity.class);
            intent.putExtra("fromTitketDetail", true);
            LoginChecker.goActivity(this, intent);
        } else {
            visitorTabAdapter.select(position);
            fillVisitorInfo(visitor);
        }
    }

    public void setTicketStatus() {
        switch (status) {
            case Status.SellStatus.UNSELLING:
                mTvDetailBuy.setBackgroundColor(ContextCompat.getColor(this, R.color.gray_hi));
                mTvDetailBuy.setText("暂未开售");
                mTvDetailBuy.setEnabled(false);
                break;
            case Status.SellStatus.SELLING:
                mTvDetailBuy.setBackgroundColor(ContextCompat.getColor(this, R.color.red_hi));
                mTvDetailBuy.setText("立即抢购");
                mTvDetailBuy.setEnabled(true);
                break;
            default:
                mTvDetailBuy.setBackgroundColor(ContextCompat.getColor(this, R.color.gray_hi));
                mTvDetailBuy.setText("抢购结束");
                mTvDetailBuy.setEnabled(false);
                break;
        }
    }


    private void fillVisitorInfo(Visitor visitor) {
        mSsllVisitorInfo.setVisibility(View.VISIBLE);
        mTvVisitorName.setText(visitor.getName());
        mTvVisitorPhone.setText(visitor.getMobile());
        mTvisitorIdcode.setText(visitor.getIdcode());
        touristId = visitor.getId();
    }

    @Subscribe
    public void addVisitor(AddVisitorEvent visitorEvent) {
        if (visitorTabAdapter.has(visitorEvent)) {
            ToastUtil.showText("已经存在该游客信息");
            return;
        }
        visitorTabAdapter.addFirst(visitorEvent);
        fillVisitorInfo(visitorEvent);
        mRvTicketDetail.scrollToPosition(0);
    }

    @Subscribe
    public void prfectVisitors(PrfectVisitorEvent visitorEvent) {
        fillVisitorInfo(visitorEvent);
        visitorTabAdapter.prfectVisitor(visitorEvent);
    }

    @Subscribe
    public void resetLoginStatus(ResetLoginStatusEvent resetLoginStatusEvent) {
        ticketDetailPresenter.getVisitors(Constants.PAGE_FIRST, Constants.PAGE_SIZE_100);
    }


}
