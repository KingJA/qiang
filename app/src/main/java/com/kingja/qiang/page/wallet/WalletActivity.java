package com.kingja.qiang.page.wallet;

import android.view.View;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.page.deallist.DealListActivity;
import com.kingja.qiang.activity.PersonalActivity;
import com.kingja.qiang.base.BaseActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.model.entiy.Wallet;
import com.kingja.qiang.util.GoUtil;
import com.kingja.supershapeview.view.SuperShapeTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/2/26 9:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class WalletActivity extends BaseActivity implements WalletContract.View {
    @BindView(R.id.stv_recharge)
    SuperShapeTextView stvRecharge;
    @BindView(R.id.stv_deal)
    SuperShapeTextView stvDeal;

    @Inject
    WalletPresenter walletPresenter;
    @BindView(R.id.tv_wallet_money)
    TextView tvWalletMoney;

    @OnClick({R.id.stv_recharge, R.id.stv_deal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_recharge:
                GoUtil.goActivity(this, PersonalActivity.class);
                break;
            case R.id.stv_deal:
                GoUtil.goActivity(this, DealListActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    public void initData() {

    }

    @Override
    public View getContentId() {
        return View.inflate(this, R.layout.activity_mine_wallet, null);
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerWalletCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        walletPresenter.attachView(this);
    }


    @Override
    protected void initNet() {
        walletPresenter.getWallet();
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
    public void onGetWalletSuccess(Wallet wallet) {
        tvWalletMoney.setText(wallet.getMoney());
    }

}
