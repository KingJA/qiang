package com.kingja.qiang.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.kingja.popwindowsir.ListPop;
import com.kingja.popwindowsir.PopConfig;
import com.kingja.popwindowsir.PopHelper;
import com.kingja.popwindowsir.PopSpinner;
import com.kingja.qiang.R;
import com.kingja.qiang.activity.SearchDetailActivity;
import com.kingja.qiang.adapter.SpinerAdapter;
import com.kingja.qiang.adapter.XigoPageAdapter;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.home.beselling.BesellFragment;
import com.kingja.qiang.page.home.selling.SellingFragment;
import com.kingja.qiang.ui.DataPop;
import com.kingja.qiang.ui.PricePop;
import com.kingja.qiang.util.GoUtil;
import com.kingja.qiang.util.IndicatorUtil;
import com.kingja.qiang.util.ToastUtil;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description:TODO
 * Create Time:2018/1/22 13:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class XigouFragment extends BaseFragment {
    @BindView(R.id.tab_xigo)
    TabLayout tabXigo;
    @BindView(R.id.ll_home_search)
    LinearLayout llHomeSearch;
    @BindView(R.id.vp_content_xigo)
    ViewPager vpContentXigo;
    @BindView(R.id.ll_spinner_root)
    LinearLayout llSpinnerRoot;
    @BindView(R.id.spiner_show_type)
    PopSpinner spinerShowType;
    @BindView(R.id.spiner_place)
    PopSpinner spinerPlace;
    @BindView(R.id.spiner_date)
    PopSpinner spinerDate;
    @BindView(R.id.spiner_price)
    PopSpinner spinerPrice;
    Unbinder unbinder;
    private String[] items = {"在售", "待售"};
    private Fragment mFragmentArr[] = new Fragment[2];
    private int[] icons = {R.mipmap.ic_selling, R.mipmap.ic_beselling};
    private String[] showTypes = {"演唱会", "话剧戏剧", "戏曲艺术", "音乐会", "体育赛事", "亲自演出", "休闲展览"};
    private String[] showPlaces = {"温州大剧院", "东南剧院", "鹿城文化中心", "温州体院馆"};
    private ListPop placePop;
    private ListPop typePop;
    private DataPop datePop;
    private PricePop pricePop;


    @OnClick({R.id.spiner_show_type, R.id.spiner_place, R.id.spiner_date, R.id.spiner_price, R.id.ll_home_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.spiner_show_type:
                typePop.showAsDropDown(llSpinnerRoot);
                break;
            case R.id.spiner_place:
                typePop.showAsDropDown(llSpinnerRoot);
                break;
            case R.id.spiner_date:
                Log.e(TAG, "日期选择: ");
                datePop.showAsDropDown(llSpinnerRoot);
                break;
            case R.id.spiner_price:
                break;
            case R.id.ll_home_search:
                GoUtil.goActivity(getActivity(),SearchDetailActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViewAndListener() {
        tabXigo.setTabMode(TabLayout.MODE_FIXED);

        tabXigo.addTab(tabXigo.newTab().setText(items[0]));
        tabXigo.addTab(tabXigo.newTab().setText(items[1]));
        tabXigo.post(() -> IndicatorUtil.setIndicator(tabXigo, 60, 60));

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


        new PopHelper.Builder(getActivity())
                .setAdapter(new SpinerAdapter(getActivity(), Arrays.asList("温州大剧院", "东南剧院", "鹿城文化中心", "温州体院馆")))
                .setPopSpinner(spinerPlace)
                .setOnItemClickListener((PopHelper.OnItemClickListener<String>) (item, position, popSpinner) -> {
                    popSpinner.setSelectText(item);
                })
                .build();

        new PopHelper.Builder(getActivity())
                .setAdapter(new SpinerAdapter(getActivity(), Arrays.asList("演唱会", "话剧戏剧", "戏曲艺术", "音乐会", "体育赛事",
                        "亲自演出", "休闲展览")))
                .setPopSpinner(spinerShowType)
                .setOnItemClickListener((PopHelper.OnItemClickListener<String>) (item, position, popSpinner) -> {
                    popSpinner.setSelectText(item);
                })
                .build();


        pricePop = new PricePop(getContext());
        pricePop.setOnDismissListener(() -> {
            spinerPrice.close();
        });
        spinerPrice.setOnSpinnerStatusChangedListener(opened -> {
            if (opened) {
                pricePop.showAsDropDown(llSpinnerRoot);
            } else {
                pricePop.dismiss();
            }
        });
        PopConfig dataPopConfig = new PopConfig.Builder()
                .setPopHeight(1000)
                .build();
        datePop = new DataPop(getActivity(), dataPopConfig);
        datePop.setOnDismissListener(() -> {
            spinerDate.close();
        });
        spinerDate.setOnSpinnerStatusChangedListener(opened -> {
            if (opened) {
                datePop.showAsDropDown(llSpinnerRoot);
            } else {
                datePop.dismiss();
            }
        });
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.frag_xigo;
    }


}