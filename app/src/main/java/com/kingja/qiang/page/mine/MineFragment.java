package com.kingja.qiang.page.mine;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.page.mine_friends.MineFriendsActivity;
import com.kingja.qiang.activity.OrderActivity;
import com.kingja.qiang.activity.PersonalActivity;
import com.kingja.qiang.page.setting.SettingActivity;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.PersonalInfo;
import com.kingja.qiang.page.discount.DiscountActivity;
import com.kingja.qiang.page.login.LoginActivity;
import com.kingja.qiang.page.message.MessageActivity;
import com.kingja.qiang.page.wallet.WalletActivity;
import com.kingja.qiang.util.GoUtil;
import com.kingja.qiang.util.SpManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description:TODO
 * Create Time:2018/1/22 13:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MineFragment extends BaseFragment implements MineContract.View {
    @BindView(R.id.iv_mine_setting)
    ImageView ivMineSetting;

    @Inject
    SpManager mSpManager;
    @BindView(R.id.ll_personal_info)
    LinearLayout llPersonalInfo;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @Inject
    MinePresenter minePresenter;
    @BindView(R.id.iv_mine_head)
    ImageView ivMineHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    Unbinder unbinder;

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
            llPersonalInfo.setVisibility(View.GONE);
        } else {
            minePresenter.getPersonalInfo(mSpManager.getUID());
        }

    }

    @Override
    protected int getContentId() {
        return R.layout.frag_mine;
    }


    @OnClick({R.id.iv_mine_setting, R.id.rl_mine_wallet, R.id.rl_mine_discount, R.id.rl_mine_frined, R.id
            .rl_mine_order, R.id.rl_mine_msg, R.id.ll_mine_personal, R.id.iv_mine_head, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_setting:
                GoUtil.goActivity(getActivity(), SettingActivity.class);
                break;
            case R.id.rl_mine_wallet:
                GoUtil.goActivity(getActivity(), WalletActivity.class);
                break;
            case R.id.rl_mine_discount:
                GoUtil.goActivity(getActivity(), DiscountActivity.class);
                break;
            case R.id.rl_mine_frined:
                GoUtil.goActivity(getActivity(), MineFriendsActivity.class);
                break;
            case R.id.rl_mine_order:
                GoUtil.goActivity(getActivity(), OrderActivity.class);
                break;
            case R.id.rl_mine_msg:
                GoUtil.goActivity(getActivity(), MessageActivity.class);
                break;
            case R.id.ll_mine_personal:
                GoUtil.goActivity(getActivity(), LoginActivity.class);
                break;
            case R.id.iv_mine_head:
                GoUtil.goActivity(getActivity(), PersonalActivity.class);
                break;
            case R.id.tv_login:
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
        llPersonalInfo.setVisibility(View.VISIBLE);

        tvNickname.setText(personalInfo.getNickname());
        tvMobile.setText(personalInfo.getMobile());
    }

}
