package com.kingja.qiang.page.modifypassword;

import android.view.View;

import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.util.CheckUtil;
import com.kingja.qiang.util.ToastUtil;
import com.kingja.supershapeview.view.SuperShapeEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/3/8 14:23
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModifyPasswordActivity extends BaseTitleActivity implements ModifyPasswordContract.View {
    @BindView(R.id.set_modifyPassword_new)
    SuperShapeEditText setModifyPasswordNew;
    @BindView(R.id.set_modifyPassword_repeat)
    SuperShapeEditText setModifyPasswordRepeat;

    @Inject
    ModifyPasswordPresenter modifyPasswordPresenter;

    @OnClick({R.id.tv_modifyPassword_confirm})
    public void click(View view) {
        String newPassword = setModifyPasswordNew.getText().toString().trim();
        String repeatPassword = setModifyPasswordRepeat.getText().toString().trim();
        if (CheckUtil.checkEmpty(newPassword, "请输入新密码") && CheckUtil.checkEmpty(repeatPassword, "请输入重复密码") &&
                CheckUtil.checkSame(newPassword, repeatPassword, "两次输入密码不一致")) {
            modifyPasswordPresenter.modifyPassword(newPassword,repeatPassword);
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerModifyPasswordCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "修改密码";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected void initView() {
        modifyPasswordPresenter.attachView(this);
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
        ToastUtil.showText("密码修改成功");
        finish();
    }
}
