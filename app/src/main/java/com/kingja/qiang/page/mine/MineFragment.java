package com.kingja.qiang.page.mine;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.activity.OrderActivity;
import com.kingja.qiang.activity.PersonalActivity;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.PersonalInfo;
import com.kingja.qiang.page.login.LoginActivity;
import com.kingja.qiang.page.message.MessageActivity;
import com.kingja.qiang.page.modifypassword.ModifyPasswordActivity;
import com.kingja.qiang.page.wallet.WalletActivity;
import com.kingja.qiang.util.GoUtil;
import com.kingja.qiang.util.SpManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/1/22 13:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MineFragment extends BaseFragment implements MineContract.View {

    @Inject
    SpManager mSpManager;
    @Inject
    MinePresenter minePresenter;
    @BindView(R.id.iv_mine_head)
    ImageView ivMineHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerMineCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initViewAndListener() {
        minePresenter.attachView(this);
    }

    @Override
    protected void initNet() {
        String token = mSpManager.getToken();
        if (TextUtils.isEmpty(token)) {
            tvLogin.setVisibility(View.VISIBLE);
            tvNickname.setVisibility(View.GONE);
        } else {
            minePresenter.getPersonalInfo(mSpManager.getUID());
        }

    }

    @Override
    protected int getContentId() {
        return R.layout.frag_mine;
    }


    @OnClick({R.id.iv_mine_msg, R.id.rl_mine_visitor, R.id.rl_mine_personal, R.id.rl_mine_password, R.id
            .rl_mine_contract, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_msg:
                //消息列表
                GoUtil.goActivity(getActivity(), MessageActivity.class);
                break;
            case R.id.rl_mine_visitor:
                //游客信息
                GoUtil.goActivity(getActivity(), WalletActivity.class);
                break;
            case R.id.rl_mine_personal:
                //个人信息
                GoUtil.goActivity(getActivity(), PersonalActivity.class);
                break;
            case R.id.rl_mine_password:
                //修改密码
                GoUtil.goActivity(getActivity(), ModifyPasswordActivity.class);
                break;
            case R.id.rl_mine_contract:
                //联系我们
                GoUtil.goActivity(getActivity(), OrderActivity.class);
                break;
            case R.id.tv_login:
                //登录
                GoUtil.goActivity(getActivity(), LoginActivity.class);
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
    public void onGetPersonalInfoSuccess(PersonalInfo personalInfo) {
        tvLogin.setVisibility(View.GONE);
        tvNickname.setVisibility(View.VISIBLE);
        tvNickname.setText(personalInfo.getNickname());
    }

}
