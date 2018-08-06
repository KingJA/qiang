package com.kingja.qiang.page.xigou;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingja.popwindowsir.PopConfig;
import com.kingja.popwindowsir.PopHelper;
import com.kingja.popwindowsir.PopSpinner;
import com.kingja.qiang.R;
import com.kingja.qiang.adapter.ScenicTypeAdapter;
import com.kingja.qiang.adapter.XigoPageAdapter;
import com.kingja.qiang.base.App;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.event.MsgCountEvent;
import com.kingja.qiang.event.ScenicType;
import com.kingja.qiang.event.TicketFilterEvent;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.City;
import com.kingja.qiang.page.message.MsgActivity;
import com.kingja.qiang.page.search.SearchDetailActivity;
import com.kingja.qiang.page.sell.beselling.BesellFragment;
import com.kingja.qiang.page.sell.selling.SellingFragment;
import com.kingja.qiang.ui.CityPop;
import com.kingja.qiang.ui.DatePop;
import com.kingja.qiang.ui.PricePop;
import com.kingja.qiang.util.AppUtil;
import com.kingja.qiang.util.GoUtil;
import com.kingja.qiang.util.IndicatorUtil;
import com.kingja.qiang.util.LogUtil;
import com.kingja.qiang.util.LoginChecker;
import com.kingja.qiang.util.SpSir;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description:嬉购主界面
 * Create Time:2018/1/22 13:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class XigouFragment extends BaseFragment implements XigouContract.View {
    @BindView(R.id.tab_xigo)
    TabLayout tabXigo;
    @BindView(R.id.ll_home_search)
    LinearLayout llHomeSearch;
    @BindView(R.id.vp_content_xigo)
    ViewPager vpContentXigo;
    @BindView(R.id.ll_spinner_root)
    LinearLayout llSpinnerRoot;
    @BindView(R.id.spiner_city)
    PopSpinner spinerCity;
    @BindView(R.id.spiner_scenicType)
    PopSpinner spinerScenicType;
    @BindView(R.id.spiner_date)
    PopSpinner spinerDate;
    @BindView(R.id.spiner_price)
    PopSpinner spinerPrice;
    @BindView(R.id.tv_xigou_search)
    TextView tvXigouSearch;
    Unbinder unbinder;
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    Unbinder unbinder1;
    @BindView(R.id.tv_msgCount)
    TextView tvMsgCount;
    private String[] items = {"在售", "待售"};
    private Fragment mFragmentArr[] = new Fragment[2];
    private int[] icons = {R.mipmap.ic_selling, R.mipmap.ic_beselling};
    private DatePop datePop;
    private PricePop pricePop;
    private List<ScenicType> scenicTypes = new ArrayList<>();
    private List<City> cities = new ArrayList<>();

    @Inject
    XigouPresenter xigouPresenter;
    private PopConfig popConfig;
    private CityPop cityPop;

    private String areaId = "";
    private String productTypeId = "";
    private String useDates = "";
    private String discountRate = "";

    @OnClick({R.id.spiner_city, R.id.spiner_scenicType, R.id.spiner_date, R.id.spiner_price, R.id.ll_home_search, R
            .id.iv_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_home_search:
                GoUtil.goActivity(getActivity(), SearchDetailActivity.class);
                break;
            case R.id.iv_msg:
                LoginChecker.goActivity(getActivity(), MsgActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initVariable() {
        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initScenicTypeData() {
        String scenicTypeGson = SpSir.getInstance().getScenicType();
        if (!TextUtils.isEmpty(scenicTypeGson)) {
            LogUtil.e(TAG, "有初始化景区类型缓存");
            scenicTypes = new Gson().fromJson(scenicTypeGson, new TypeToken<List<ScenicType>>() {
            }.getType());
        } else {
            LogUtil.e(TAG, "没有初始化景区类型缓存");
            xigouPresenter.getScenicType("3");
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
            xigouPresenter.getCity();
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerXigouCompnent.builder()
                .appComponent(App.getContext().getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    protected void initViewAndListener() {
        xigouPresenter.attachView(this);
        initHint();
        popConfig = new PopConfig.Builder()
                .setPopHeight((int) (AppUtil.getScreenHeight() * 0.55f))
                .build();
        initTablayout();
        initScenicTypeData();
        initScenicTypePop();
        initCityData();
        initCityPop();
        initDatePop();
        initPricePop();


    }

    private void initHint() {
        String historyKeyword = SpSir.getInstance().getHistoryKeyword();
        Log.e(TAG, "historyKeyword: " + historyKeyword);
        if (!TextUtils.isEmpty(historyKeyword)) {
            tvXigouSearch.setHint(historyKeyword);
        }
    }

    private void initPricePop() {
        pricePop = new PricePop(getContext());
        pricePop.setOnDismissListener(() -> {
            spinerPrice.close();
        });
        pricePop.setOnDiscountRateSelectedLintener(new PricePop.OnDiscountRateSelectedLintener() {
            @Override
            public void onDiscountRateSelected(String discount) {
                Log.e(TAG, "折扣: " + discount);
                discountRate = discount;
                EventBus.getDefault().post(new TicketFilterEvent(areaId, productTypeId, useDates, discountRate));
            }
        });
        spinerPrice.setOnSpinnerStatusChangedListener(opened -> {
            if (opened) {
                pricePop.showAsDropDown(llSpinnerRoot);
            } else {
                pricePop.dismiss();
            }
        });
    }

    private void initDatePop() {
        datePop = new DatePop(getActivity(), popConfig);
        datePop.setOnDismissListener(() -> {
            spinerDate.close();
        });
        datePop.setOnDateSelectedListener(dates -> {
            Log.e(TAG, "选择日期: " + dates);
            useDates = dates;
            datePop.dismiss();
            EventBus.getDefault().post(new TicketFilterEvent(areaId, productTypeId, useDates, discountRate));
        });
        spinerDate.setOnSpinnerStatusChangedListener(opened -> {
            if (opened) {
                datePop.showAsDropDown(llSpinnerRoot);
            } else {
                datePop.dismiss();
            }
        });
    }

    private void initTablayout() {
        tabXigo.setTabMode(TabLayout.MODE_FIXED);
        tabXigo.addTab(tabXigo.newTab().setText(items[0]));
        tabXigo.addTab(tabXigo.newTab().setText(items[1]));
        tabXigo.post(() -> IndicatorUtil.setIndicator(tabXigo, 50, 50));
        mFragmentArr[0] = new SellingFragment();
        mFragmentArr[1] = new BesellFragment();
        XigoPageAdapter mHigoPageAdapter = new XigoPageAdapter(getActivity(), getChildFragmentManager(), mFragmentArr,
                items, icons);
        vpContentXigo.setAdapter(mHigoPageAdapter);
        vpContentXigo.setOffscreenPageLimit(2);
        tabXigo.setupWithViewPager(vpContentXigo);
        for (int i = 0; i < tabXigo.getTabCount(); i++) {
            TabLayout.Tab tab = tabXigo.getTabAt(i);
            tab.setCustomView(mHigoPageAdapter.getTabView(i));
        }
    }


    private void initScenicTypePop() {
        if (scenicTypes != null && scenicTypes.size() > 0) {
            ScenicType scenicType = new ScenicType();
            scenicType.setCode("");
            scenicType.setDesc("不限");
            scenicTypes.add(0, scenicType);
            ScenicTypeAdapter scenicTypeAdapter = new ScenicTypeAdapter(getActivity(), scenicTypes);
            new PopHelper.Builder(getActivity())
                    .setAdapter(scenicTypeAdapter)
                    .setPopSpinner(spinerScenicType)
                    .setOnItemClickListener((PopHelper.OnItemClickListener<ScenicType>) (item, position, popSpinner)
                            -> {
                        Log.e(TAG, "景区ID: " + item.getCode() + " 景区名称: " + item.getDesc());
                        productTypeId = item.getCode();
                        scenicTypeAdapter.selectItem(position);
                        EventBus.getDefault().post(new TicketFilterEvent(areaId, productTypeId, useDates,
                                discountRate));
                    })
                    .build();
        }
    }

    private void initCityPop() {
        if (cities != null && cities.size() > 0) {
            cityPop = new CityPop(getActivity(), popConfig);
            cityPop.setCities(cities);
            cityPop.setOnAreaSelectListener((areaId, areaName) -> {
                Log.e(TAG, "区域ID: " + areaId + " 区域名称: " + areaName);
                this.areaId = areaId;
                EventBus.getDefault().post(new TicketFilterEvent(areaId, productTypeId, useDates, discountRate));
                cityPop.dismiss();
            });
            cityPop.setOnDismissListener(() -> {
                spinerCity.close();
            });
            spinerCity.setOnSpinnerStatusChangedListener(opened -> {
                if (opened) {
                    cityPop.showAsDropDown(llSpinnerRoot);
                } else {
                    cityPop.dismiss();
                }
            });
        }

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.frag_xigo;
    }


    @Override
    public void onGetScenicTypeSuccess(List<ScenicType> scenicTypes) {
        this.scenicTypes = scenicTypes;
        initScenicTypePop();
    }

    @Override
    public void onGetCitySuccess(List<City> cities) {
        this.cities = cities;
        Log.e(TAG, "chongxin jiazai: " + cities);
        initCityPop();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onStart() {
        super.onStart();
        initHint();
        resetMsgCount();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshMsgCount(MsgCountEvent msgCountEvent) {
        resetMsgCount();
    }

    private void resetMsgCount() {
        int msgCount = SpSir.getInstance().getMsgCount();
        if (msgCount != 0) {
            tvMsgCount.setVisibility(View.VISIBLE);
            tvMsgCount.setText(String.valueOf(msgCount));
        } else {
            tvMsgCount.setVisibility(View.GONE);
        }
    }

}