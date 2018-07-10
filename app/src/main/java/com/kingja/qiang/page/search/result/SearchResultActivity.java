package com.kingja.qiang.page.search.result;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseActivity;
import com.kingja.qiang.injector.component.AppComponent;

/**
 * Description:TODO
 * Create Time:2018/7/10 16:08
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SearchResultActivity extends BaseActivity {

    private String keyword;
    private LinearLayout mLlTitleBack;
    private EditText mEtSearchKeyword;
    private ImageView mIvClearInput;
    private TextView mTvSearch;
    private View rootView;


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

    }

    @Override
    protected void initView() {
        mLlTitleBack = rootView.findViewById(R.id.ll_title_back);
        mEtSearchKeyword = rootView.findViewById(R.id.et_search_keyword);
        mIvClearInput = rootView.findViewById(R.id.iv_clearInput);
        mTvSearch = rootView.findViewById(R.id.tv_search);
    }

    @Override
    protected void initData() {
        mEtSearchKeyword.setText(keyword);
    }

    @Override
    protected void initNet() {

    }

    public static void goActivity(Context context, String keyword) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra("keyword", keyword);
        context.startActivity(intent);
    }
}
