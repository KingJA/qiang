package com.kingja.qiang.base;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.injector.component.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description：TODO
 * Create Time：2017/3/20 14:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseTitleActivity extends BaseActivity {

    protected View rootView;
    private TextView tvTitleTitle;
    private Unbinder bind;


    @Override
    public View getContentId() {
        rootView = View.inflate(this, R.layout.activity_title, null);
        FrameLayout flContent = rootView.findViewById(R.id.fl_content);
        tvTitleTitle = rootView.findViewById(R.id.tv_title_title);
        LinearLayout llTitleBack = rootView.findViewById(R.id.ll_title_back);
        tvTitleTitle.setText(getContentTitle() == null ? "" : getContentTitle());
        llTitleBack.setOnClickListener(v -> finish());
        View content = View.inflate(this, getContentView(), null);
        if (content != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            flContent.addView(content, params);
            bind = ButterKnife.bind(this, rootView);
            // register after ButterKnife.bind()

        }
        return rootView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }

    public void setContentTitle(String title) {
        tvTitleTitle.setText(title);
    }

    public void setMenuRes(int resId, View.OnClickListener onClickListener) {
        LinearLayout llTitleMenu = rootView.findViewById(R.id.ll_title_menu);
        llTitleMenu.setVisibility(View.VISIBLE);
        llTitleMenu.setOnClickListener(onClickListener);
        ImageView ivMenu = rootView.findViewById(R.id.iv_menu);
        ivMenu.setBackgroundResource(resId);
    }

    public void setRightClick(String rightText, View.OnClickListener onClickListener) {
        TextView tv_right_text = rootView.findViewById(R.id.tv_right_text);
        tv_right_text.setText(rightText);
        tv_right_text.setVisibility(View.VISIBLE);
        tv_right_text.setOnClickListener(onClickListener);
    }

    @Override
    public abstract void initVariable();

    @Override
    protected abstract void initComponent(AppComponent appComponent);


    protected abstract String getContentTitle();

    protected abstract int getContentView();

    @Override
    protected abstract void initView();

    @Override
    protected abstract void initData();

    @Override
    protected abstract void initNet();


}
