package com.kingja.qiang.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.supershapeview.view.SuperShapeTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:联系我们
 * Create Time:2018/7/2 16:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactUsActivity extends BaseTitleActivity {
    @BindView(R.id.stv_contact_phone)
    SuperShapeTextView stvContactPhone;

    @OnClick({R.id.stv_contact_phone})
    public void click(View view) {
        callPhone("057788218708");
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "联系我们";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_contact;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {

    }

}
