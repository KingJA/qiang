package com.kingja.qiang.page.setting;

import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.RelativeLayout;

import com.kingja.qiang.R;
import com.kingja.qiang.activity.ModifyPhoneActivity;
import com.kingja.qiang.page.mine.headimg.PersonalActivity;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.discount.DiscountActivity;
import com.kingja.qiang.page.modifypassword.ModifyPasswordActivity;
import com.kingja.qiang.util.GoUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/2/26 9:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SettingActivity extends BaseTitleActivity {
    @BindView(R.id.rl_setting_personal)
    RelativeLayout rlSettingPersonal;
    @BindView(R.id.rl_setting_modifyPhone)
    RelativeLayout rlSettingModifyPhone;
    @BindView(R.id.rl_setting_modifyPassword)
    RelativeLayout rlSettingModifyPassword;
    @BindView(R.id.switch_receiveMsg)
    SwitchCompat switchReceiveMsg;
    @BindView(R.id.rl_setting_help)
    RelativeLayout rlSettingHelp;
    @BindView(R.id.rl_setting_aboutUs)
    RelativeLayout rlSettingAboutUs;


    @OnClick({R.id.rl_setting_personal, R.id.rl_setting_modifyPhone, R.id.rl_setting_modifyPassword, R.id
            .rl_setting_help, R.id.rl_setting_aboutUs, R.id.stv_setting_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_setting_personal:
                GoUtil.goActivity(this, PersonalActivity.class);
                break;
            case R.id.rl_setting_modifyPhone:
                GoUtil.goActivity(this, ModifyPhoneActivity.class);
                break;
            case R.id.rl_setting_modifyPassword:
                GoUtil.goActivity(this, ModifyPasswordActivity.class);
                break;
            case R.id.rl_setting_help:
                GoUtil.goActivity(this, DiscountActivity.class);
                break;
            case R.id.rl_setting_aboutUs:
                GoUtil.goActivity(this, DiscountActivity.class);
                break;
            case R.id.stv_setting_quit:
                break;

            default:
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "设置";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_setting;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initNet() {

    }

}
