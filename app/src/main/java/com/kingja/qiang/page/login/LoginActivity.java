package com.kingja.qiang.page.login;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.event.ResetLoginStatusEvent;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.Login;
import com.kingja.qiang.page.forgetpassword.ForgetPasswordActivity;
import com.kingja.qiang.page.register.RegisterActivity;
import com.kingja.qiang.util.CheckUtil;
import com.kingja.qiang.util.EncryptUtil;
import com.kingja.qiang.util.GoUtil;
import com.kingja.qiang.util.SpSir;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * Description:登录
 * Create Time:2018/3/8 13:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoginActivity extends BaseTitleActivity implements LoginContract.View {
    @Inject
    LoginPresenter mLoginPresenter;
    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.tv_login_confirm)
    TextView tvLoginConfirm;
    @BindView(R.id.tv_login_register)
    TextView etLoginRegister;
    @BindView(R.id.tv_login_findPassword)
    TextView etLoginFindPassword;

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerLoginCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return getString(R.string.title_login);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mLoginPresenter.attachView(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {

    }

    @OnClick({R.id.tv_login_findPassword, R.id.tv_login_register, R.id.tv_login_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_findPassword:
                GoUtil.goActivity(this, ForgetPasswordActivity.class);
                break;
            case R.id.tv_login_register:
                GoUtil.goActivity(this, RegisterActivity.class);
                break;
            case R.id.tv_login_confirm:
                String username = etLoginName.getText().toString().trim();
                String password = etLoginPassword.getText().toString().trim();
                if (CheckUtil.checkPhoneFormat(username) && CheckUtil.checkEmpty(password, "请输入密码")) {
                    mLoginPresenter.login(username, EncryptUtil.getMd5(password), JPushInterface.getRegistrationID(this), "", "");
                }
                break;
            default:
                break;
        }
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
    public void onLoginSuccess(Login login) {
        save2Localhost(login);
        EventBus.getDefault().post(new ResetLoginStatusEvent());
        finish();
    }

    private void save2Localhost(Login login) {
        SpSir.getInstance().putHeadImg(login.getHeadImg());
        SpSir.getInstance().putUserId(login.getUserId());
        SpSir.getInstance().putToken(login.getToken());
        SpSir.getInstance().putMobile(login.getMobile());
        SpSir.getInstance().putNickName(login.getNickName());
    }

}