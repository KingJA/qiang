package com.kingja.qiang.page.forgetpassword;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.constant.VariableConstant;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.util.CheckUtil;
import com.kingja.qiang.util.CountTimer;
import com.kingja.qiang.util.ToastUtil;
import com.kingja.supershapeview.view.SuperShapeTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Description:TODO
 * Create Time:2018/3/8 13:43
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ForgetPasswordActivity extends BaseTitleActivity implements ForgetPasswordContract.View {
    @BindView(R.id.et_register_mobile)
    EditText etRegisterMobile;
    @BindView(R.id.et_register_code)
    EditText etRegisterCode;
    @BindView(R.id.stv_register_getCode)
    SuperShapeTextView stvRegisterGetCode;
    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;
    @BindView(R.id.iv_register_showPassword)
    ImageView ivRegisterShowPassword;
    @BindView(R.id.tv_register_confirm)
    TextView tvRegisterConfirm;
    private CountTimer countTimer;
    private boolean isShow;
    @Inject
    ForgetPasswordPresenter forgetPasswordPresenter;

    @OnClick({R.id.stv_register_getCode, R.id.iv_register_showPassword, R.id.tv_register_confirm})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.stv_register_getCode:
                String mobile = etRegisterMobile.getText().toString().trim();
                if (CheckUtil.checkPhoneFormat(mobile)) {
                    getCode(mobile);
                }
                break;
            case R.id.iv_register_showPassword:
                switchPasswrodShowd();

                break;
            case R.id.tv_register_confirm:
                register();
                break;
            default:
                break;

        }

    }


    @Override
    public void initVariable() {

    }

    private void switchPasswrodShowd() {
        isShow = !isShow;
        if (isShow) {
            etRegisterPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            etRegisterPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        etRegisterPassword.setSelection(etRegisterPassword.getText().length());
    }

    private void register() {
        String mobile = etRegisterMobile.getText().toString().trim();
        String code = etRegisterCode.getText().toString().trim();
        String password = etRegisterPassword.getText().toString().trim();
        if (CheckUtil.checkPhoneFormat(mobile) && CheckUtil.checkEmpty(code, "请输入验证码") && CheckUtil.checkEmpty
                (password, "请输入密码")) {
            forgetPasswordPresenter.modifyPassword(mobile, password, code);
        }
    }


    private void getCode(String mobile) {
        countTimer = new CountTimer(10, stvRegisterGetCode);
        stvRegisterGetCode.setClickable(false);
        countTimer.start();
        forgetPasswordPresenter.getCode(mobile, VariableConstant.SmsType.BACK);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countTimer != null) {
            countTimer.cancel();
        }
    }


    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerForgetPasswordCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "找回密码";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        forgetPasswordPresenter.attachView(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {

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
    public void onModifyPasswordSuccess() {
        ToastUtil.showText("密码设置成功");
        finish();
    }

    @Override
    public void onGetCodeSuccess() {
        ToastUtil.showText("已发送验证码至该手机");
    }
}
