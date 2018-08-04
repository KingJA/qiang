package com.kingja.qiang.page.search.result;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.popwindowsir.PopConfig;
import com.kingja.popwindowsir.PopHelper;
import com.kingja.popwindowsir.PopSpinner;
import com.kingja.qiang.R;
import com.kingja.qiang.adapter.AllTicketAdapter;
import com.kingja.qiang.adapter.ScenicTypeAdapter;
import com.kingja.qiang.base.BaseActivity;
import com.kingja.qiang.callback.EmptyTicketCallback;
import com.kingja.qiang.callback.TicketCallback;
import com.kingja.qiang.constant.Constants;
import com.kingja.qiang.event.ScenicType;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.City;
import com.kingja.qiang.page.detail.TicketDetailActivity;
import com.kingja.qiang.page.sell.Ticket;
import com.kingja.qiang.page.sell.TicketContract;
import com.kingja.qiang.page.sell.TicketPresenter;
import com.kingja.qiang.ui.CityPop;
import com.kingja.qiang.ui.DatePop;
import com.kingja.qiang.ui.PricePop;
import com.kingja.qiang.util.AppUtil;
import com.kingja.qiang.util.LogUtil;
import com.kingja.qiang.util.SpSir;
import com.kingja.qiang.util.ToastUtil;
import com.kingja.qiang.view.PullToBottomListView;
import com.kingja.qiang.view.RefreshSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/7/10 16:08
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SearchResultActivity extends BaseActivity implements TicketContract.View, SwipeRefreshLayout
        .OnRefreshListener, PullToBottomListView.OnScrollToBottom {

    private String keyword = "";
    private LinearLayout mLlTitleBack;
    private EditText mEtSearchKeyword;
    private ImageView mIvClearInput;
    private TextView mTvSearch;
    private View rootView;
    @Inject
    TicketPresenter ticketPresenter;
    private String areaId = "";
    private String productTypeId = "";
    private String useDates = "";
    private String discountRate = "";
    private RefreshSwipeRefreshLayout srl;
    private PullToBottomListView lv;
    private LoadService loadService;
    private List<Ticket> tickets = new ArrayList<>();
    private int page = 1;
    private boolean hasMore;
    private AllTicketAdapter allTicketAdapter;
    private PopSpinner spiner_city;
    private PopSpinner spiner_scenicType;
    private PopSpinner spiner_date;
    private PopSpinner spiner_price;
    private LinearLayout ll_spinner_root;
    private List<ScenicType> scenicTypes;
    private List<City> cities;
    private PopConfig popConfig;
    private ImageView ivGoTop;

    @OnClick({R.id.tv_search, R.id.ll_title_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                initNet();
                break;
            case R.id.ll_title_back:
                finish();
                break;
            default:
                break;
        }
    }

    @OnItemClick(R.id.lv)
    public void itemClick(AdapterView<?> parent, View view, int position, long id) {
        Ticket ticket = (Ticket) parent.getItemAtPosition(position);
        TicketDetailActivity.goActivity(this, ticket.getId());
    }

    @Override
    public void initVariable() {
        keyword = getIntent().getStringExtra("keyword");

    }

    @Override
    public View getContentId() {
        rootView = View.inflate(this, R.layout.activity_search_result, null);
        return rootView;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerSearchResultCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        ticketPresenter.attachView(this);
        mLlTitleBack = rootView.findViewById(R.id.ll_title_back);
        mEtSearchKeyword = rootView.findViewById(R.id.et_search_keyword);
        mIvClearInput = rootView.findViewById(R.id.iv_clearInput);
        mTvSearch = rootView.findViewById(R.id.tv_search);
        srl = rootView.findViewById(R.id.srl);
        lv = rootView.findViewById(R.id.lv);
        ivGoTop = rootView.findViewById(R.id.iv_go_top);

        ll_spinner_root = rootView.findViewById(R.id.ll_spinner_root);
        spiner_city = rootView.findViewById(R.id.spiner_city);
        spiner_scenicType = rootView.findViewById(R.id.spiner_scenicType);
        spiner_date = rootView.findViewById(R.id.spiner_date);
        spiner_price = rootView.findViewById(R.id.spiner_price);

        srl.setScrollUpChild(lv);
        srl.setOnRefreshListener(this);
        lv.setOnScrollToBottom(this);
        allTicketAdapter = new AllTicketAdapter(this, tickets);
        lv.setAdapter(allTicketAdapter);
        lv.setGoTop(ivGoTop);
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new TicketCallback())
                .addCallback(new EmptyTicketCallback())
                .setDefaultCallback(TicketCallback.class)
                .build();
        loadService = loadSir.register(lv);
    }

    @Override
    protected void initData() {
        mEtSearchKeyword.setText(keyword);
        popConfig = new PopConfig.Builder()
                .setPopHeight((int) (AppUtil.getScreenHeight() * 0.5f))
                .build();
        initScenicTypeData();
        initScenicTypePop();
        initCityData();
        initCityPop();
        initDatePop();
        initPricePop();

    }

    @Override
    protected void initNet() {
        page = Constants.PAGE_FIRST;
        ticketPresenter.getTickets(areaId, productTypeId, useDates, discountRate, keyword, page, Constants.PAGE_SIZE,
                2);
    }

    public static void goActivity(Context context, String keyword) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra("keyword", keyword);
        context.startActivity(intent);
    }

    @Override
    public void onGetTicketSuccess(List<Ticket> tickets) {
        hasMore = tickets.size() == Constants.PAGE_SIZE;
        if (tickets.size() == 0) {
            loadService.showCallback(EmptyTicketCallback.class);
        } else {
            loadService.showSuccess();
            allTicketAdapter.setData(tickets);
        }
    }

    @Override
    public void onGetMoreTicketSuccess(List<Ticket> tickets) {
        hasMore = tickets.size() == Constants.PAGE_SIZE;
        allTicketAdapter.addData(tickets);
    }

    @Override
    public void showLoading() {
        srl.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srl.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        initNet();
    }

    @Override
    public void onScrollToBottom() {
        if (srl.isRefreshing()) {
            return;
        }
        if (hasMore) {
            page++;
            ticketPresenter.getMoreTickets(areaId, productTypeId, useDates, discountRate, keyword, page, Constants
                    .PAGE_SIZE, 2);
        } else {
            ToastUtil.showText("到底啦");
        }
    }
    private void initCityData() {
        String cityGson = SpSir.getInstance().getCity();
        if (!TextUtils.isEmpty(cityGson)) {
            LogUtil.e(TAG, "有初始化城市缓存:" + cityGson);
            cities = new Gson().fromJson(cityGson, new TypeToken<List<City>>() {
            }.getType());
        } else {
            LogUtil.e(TAG, "没有初始化城市缓存");
        }
    }
    private void initCityPop() {
        if (cities != null && cities.size() > 0) {
            CityPop cityPop = new CityPop(this, popConfig);
            cityPop.setCities(cities);
            cityPop.setOnAreaSelectListener((areaId, areaName) -> {
                Log.e(TAG, "区域ID: " + areaId + " 区域名称: " + areaName);
                this.areaId = areaId;
                ticketPresenter.getTickets(areaId, productTypeId, useDates, discountRate, keyword, 1,
                        Constants.PAGE_SIZE, 2);
                cityPop.dismiss();
            });
            cityPop.setOnDismissListener(() -> {
                spiner_city.close();
            });
            spiner_city.setOnSpinnerStatusChangedListener(opened -> {
                if (opened) {
                    cityPop.showAsDropDown(ll_spinner_root);
                } else {
                    cityPop.dismiss();
                }
            });
        }

    }
    private void initScenicTypeData() {
        String scenicTypeGson = SpSir.getInstance().getScenicType();
        if (!TextUtils.isEmpty(scenicTypeGson)) {
            LogUtil.e(TAG, "有初始化景区类型缓存");
            scenicTypes = new Gson().fromJson(scenicTypeGson, new TypeToken<List<ScenicType>>() {
            }.getType());
        } else {
            LogUtil.e(TAG, "没有初始化景区类型缓存");
        }
    }

    private void initScenicTypePop() {
        if (scenicTypes != null && scenicTypes.size() > 0) {
            ScenicType scenicType = new ScenicType();
            scenicType.setCode("");
            scenicType.setDesc("不限");
            scenicTypes.add(0, scenicType);
            ScenicTypeAdapter scenicTypeAdapter = new ScenicTypeAdapter(this, scenicTypes);
            new PopHelper.Builder(this)
                    .setAdapter(scenicTypeAdapter)
                    .setPopSpinner(spiner_scenicType)
                    .setOnItemClickListener((PopHelper.OnItemClickListener<ScenicType>) (item, position, popSpinner)
                            -> {
                        Log.e(TAG, "景区ID: " + item.getCode() + " 景区名称: " + item.getDesc());
                        productTypeId = item.getCode();
                        scenicTypeAdapter.selectItem(position);
                        ticketPresenter.getTickets(areaId, productTypeId, useDates, discountRate, keyword, 1,
                                Constants.PAGE_SIZE, 2);
                    })
                    .build();
        }
    }

    private void initDatePop() {
        DatePop datePop = new DatePop(this, popConfig);
        datePop.setOnDismissListener(() -> {
            spiner_date.close();
        });
        datePop.setOnDateSelectedListener(dates -> {
            Log.e(TAG, "选择日期: " + dates);
            useDates = dates;
            datePop.dismiss();
            ticketPresenter.getTickets(areaId, productTypeId, useDates, discountRate, keyword, 1,
                    Constants.PAGE_SIZE, 2);
        });
        spiner_date.setOnSpinnerStatusChangedListener(opened -> {
            if (opened) {
                datePop.showAsDropDown(ll_spinner_root);
            } else {
                datePop.dismiss();
            }
        });
    }

    private void initPricePop() {
        PricePop  pricePop = new PricePop(this);
        pricePop.setOnDismissListener(() -> {
            spiner_price.close();
        });
        pricePop.setOnDiscountRateSelectedLintener(new PricePop.OnDiscountRateSelectedLintener() {
            @Override
            public void onDiscountRateSelected(String discount) {
                Log.e(TAG, "折扣: "+discount );
                discountRate=discount;
                ticketPresenter.getTickets(areaId, productTypeId, useDates, discountRate, keyword, 1,
                        Constants.PAGE_SIZE, 2);
            }
        });
        spiner_price.setOnSpinnerStatusChangedListener(opened -> {
            if (opened) {
                pricePop.showAsDropDown(ll_spinner_root);
            } else {
                pricePop.dismiss();
            }
        });
    }
}
