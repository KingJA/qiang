package com.kingja.qiang.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.util.ToastUtil;
import com.orhanobut.logger.Logger;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Arrays;

/**
 * Description:TODO
 * Create Time:2018/7/9 16:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SearchDetailActivity extends BaseActivity implements TextView.OnEditorActionListener {

    private View rootView;
    private LinearLayout mLlTitleBack;
    private EditText mEtSearchKeyword;
    private TextView mTvSearch;
    private TagFlowLayout mTflSearchHistory;


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
        mTflSearchHistory = rootView.findViewById(R.id.tfl_search_history);
    }

    @Override
    protected void initData() {
        mEtSearchKeyword.setOnEditorActionListener(this);
        mEtSearchKeyword.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        mTflSearchHistory.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                return true;
            }
        });
        mTflSearchHistory.setAdapter(new TagAdapter<String>(Arrays.asList("大罗山","雁荡山","雁荡山","雁荡山","雁荡山","雁荡山")) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                return null;
            }
        });
    }

    @Override
    protected void initNet() {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            doSearch(v.getText().toString().trim());
            return true;
        }
        return false;
    }

    private void doSearch(String keyword) {
        ToastUtil.showText("搜索:" + keyword);
    }
}
