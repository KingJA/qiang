package com.kingja.qiang.page.search;

import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.HotSearch;
import com.kingja.qiang.page.search.result.SearchResultActivity;
import com.kingja.qiang.util.DialogUtil;
import com.kingja.qiang.util.SpSir;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:热搜和历史搜索
 * Create Time:2018/7/9 16:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SearchDetailActivity extends BaseActivity implements TextView.OnEditorActionListener, View
        .OnClickListener, TextWatcher {

    private View rootView;
    private LinearLayout mLlTitleBack;
    private EditText mEtSearchKeyword;
    private TextView mTvSearch;
    private TagFlowLayout mTflHotsearch;
    private TagFlowLayout mTflHistory;
    private List<HotSearch> hotSearches = new ArrayList<>();
    private LinearLayout mLlHistoryClear;
    private LinearLayout mLlHotSearch;
    private LinearLayout mLlHistory;
    private String[] historySearchs;
    private ImageView mIvClearInput;


    @Override
    public void initVariable() {


    }

    @Override
    public View getContentId() {
        rootView = View.inflate(this, R.layout.activity_search_detail, null);
        return rootView;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initView() {
        mLlTitleBack = rootView.findViewById(R.id.ll_title_back);
        mEtSearchKeyword = rootView.findViewById(R.id.et_search_keyword);
        mTvSearch = rootView.findViewById(R.id.tv_search);
        mTflHotsearch = rootView.findViewById(R.id.tfl_hotSearch);
        mLlHistoryClear = rootView.findViewById(R.id.ll_history_clear);
        mTflHistory = rootView.findViewById(R.id.tfl_history);
        mLlHotSearch = rootView.findViewById(R.id.ll_hotSearch);
        mLlHistory = rootView.findViewById(R.id.ll_history);
        mIvClearInput = rootView.findViewById(R.id.iv_clearInput);
    }

    @Override
    protected void initData() {
        initHint();
        initLocalhostData();
        mEtSearchKeyword.setOnEditorActionListener(this);
        mEtSearchKeyword.addTextChangedListener(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        mTvSearch.setOnClickListener(this);
        mIvClearInput.setOnClickListener(this);
        mLlTitleBack.setOnClickListener(this);
    }
    private void initHint() {
        String historyKeyword = SpSir.getInstance().getHistoryKeyword();
        if (!TextUtils.isEmpty(historyKeyword)) {
            mEtSearchKeyword.setHint(historyKeyword);
        }
    }
    private void initLocalhostData() {
        String hotSearch = SpSir.getInstance().getHotSearch();
        if (!TextUtils.isEmpty(hotSearch)) {
            mLlHotSearch.setVisibility(View.VISIBLE);
            hotSearches = new Gson().fromJson(hotSearch, new TypeToken<List<HotSearch>>() {
            }.getType());
            mTflHotsearch.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    doSaveSearch(hotSearches.get(position).getKeyword());
                    return true;
                }
            });
            mTflHotsearch.setAdapter(new TagAdapter<HotSearch>(hotSearches) {
                @Override
                public View getView(FlowLayout parent, int position, HotSearch hotSearch) {
                    TextView tv = (TextView) View.inflate(SearchDetailActivity.this, R.layout.single_tag_textview,
                            null);
                    tv.setText(hotSearch.getKeyword());
                    if (hotSearch.getIshighlight() == 1) {
                        tv.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.red_hi));
                    } else {
                        tv.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.c_3));
                    }
                    return tv;
                }
            });
        }
        initHistorySearchStatus();
    }

    private void initHistorySearchStatus() {
        String historySearch = SpSir.getInstance().getHistorySearch();
        if (!TextUtils.isEmpty(historySearch)) {
            mLlHistory.setVisibility(View.VISIBLE);
            mLlHistoryClear.setOnClickListener(this);
            historySearchs = historySearch.split("#");
            mTflHistory.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    doSearch(historySearchs[position]);
                    return true;
                }
            });
            mTflHistory.setAdapter(new TagAdapter<String>(historySearchs) {
                @Override
                public View getView(FlowLayout parent, int position, String historySearch) {
                    TextView tv = (TextView) View.inflate(SearchDetailActivity.this, R.layout.single_tag_textview,
                            null);
                    tv.setText(historySearch);
                    return tv;
                }
            });
        } else {
            mLlHistory.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initNet() {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            doSaveSearch(v.getText().toString().trim());
            return true;
        }
        return false;
    }

    private void doSearch(String keyword) {
        mEtSearchKeyword.setText(keyword);
        mEtSearchKeyword.setSelection(keyword.length());
        SearchResultActivity.goActivity(this,keyword);
    }

    private void doSaveSearch(String keyword) {
        SpSir.getInstance().putHistoryKeyword(keyword);
        SpSir.getInstance().addHistorySearch(keyword);
        initHistorySearchStatus();
        doSearch(keyword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_history_clear:
                DialogUtil.showDoubleDialog(this, "确认删除搜索历史?", (dialog, which) -> {
                    SpSir.getInstance().clearHistorySearch();
                    mLlHistory.setVisibility(View.GONE);
                });
                break;
            case R.id.tv_search:
                String keyword = mEtSearchKeyword.getText().toString().trim();
                doSaveSearch(keyword);
                break;
            case R.id.iv_clearInput:
                mEtSearchKeyword.setText("");
                break;
            case R.id.ll_title_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mIvClearInput.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
    }
}
