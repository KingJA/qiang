package com.kingja.qiang.page.mine;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kingja.qiang.R;
import com.kingja.qiang.activity.ContactUsActivity;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.event.RefreshHeadImgEvent;
import com.kingja.qiang.event.RefreshNicknameEvent;
import com.kingja.qiang.event.ResetLoginStatusEvent;
import com.kingja.qiang.imgaeloader.ImageLoader;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.login.LoginActivity;
import com.kingja.qiang.page.message.MsgActivity;
import com.kingja.qiang.page.mine.headimg.PersonalActivity;
import com.kingja.qiang.page.modifypassword.ModifyPasswordActivity;
import com.kingja.qiang.page.visitor.list.VisitorListActivity;
import com.kingja.qiang.util.GoUtil;
import com.kingja.qiang.util.LoginChecker;
import com.kingja.qiang.util.SpSir;
import com.kingja.supershapeview.view.SuperShapeImageView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/1/22 13:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MineFragment extends BaseFragment implements MineContract.View{

    @BindView(R.id.iv_mine_head)
    SuperShapeImageView ivMineHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_quit)
    TextView tvQuit;

    @Inject
    MinePresenter minePresenter;

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
        EventBus.getDefault().register(this);
        initLoginStatus();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initLoginStatus() {
        String token = SpSir.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            //已登录
            String nickname = SpSir.getInstance().getNickname();
            String headImg = SpSir.getInstance().getHeadImg();
            ImageLoader.getInstance().loadImage(getActivity(), headImg, ivMineHead);
            tvNickname.setText(nickname);
            tvQuit.setVisibility(View.VISIBLE);
            tvNickname.setOnClickListener(v -> {
                GoUtil.goActivity(getActivity(), PersonalActivity.class);
            });
        } else {
            //未登录
            tvQuit.setVisibility(View.GONE);
            tvNickname.setText("注册/登录");
            ivMineHead.setImageResource(R.mipmap.ic_logo);
            tvNickname.setOnClickListener(v -> {
                GoUtil.goActivity(getActivity(), LoginActivity.class);
            });
        }
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.frag_mine;
    }


    @OnClick({R.id.iv_mine_msg, R.id.rl_mine_visitor, R.id.rl_mine_personal, R.id.rl_mine_password, R.id
            .rl_mine_contract, R.id.tv_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_msg:
                //消息列表
                LoginChecker.goActivity(getActivity(), MsgActivity.class);
                break;
            case R.id.rl_mine_visitor:
                //游客信息
                LoginChecker.goActivity(getActivity(), VisitorListActivity.class);
                break;
            case R.id.rl_mine_personal:
                //个人信息
                LoginChecker.goActivity(getActivity(), PersonalActivity.class);
                break;
            case R.id.rl_mine_password:
                //修改密码
                LoginChecker.goActivity(getActivity(), ModifyPasswordActivity.class);
                break;
            case R.id.rl_mine_contract:
                //联系我们
                GoUtil.goActivity(getActivity(), ContactUsActivity.class);
                break;
            case R.id.tv_quit:
                //退出登录
                showQuitDialog();
                break;
            default:
                break;
        }
    }

    public void showQuitDialog() {
        new MaterialDialog.Builder(getActivity())
                .content("确认退出账号?")
                .positiveText("确认")
                .negativeText("取消")
                .positiveColor(ContextCompat.getColor(getActivity(), R.color.gray_hi))
                .onPositive((dialog, which) -> {
                    quit();
                })
                .show();
    }

    private void quit() {
        SpSir.getInstance().clearData();
        EventBus.getDefault().post(new ResetLoginStatusEvent());
        minePresenter.logout();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resetLoginStatus(ResetLoginStatusEvent resetLoginStatusEvent) {
        initLoginStatus();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setNickname(RefreshNicknameEvent refreshNicknameEvent) {
        tvNickname.setText(SpSir.getInstance().getNickname());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setHeadImg(RefreshHeadImgEvent refreshHeadImgEvent) {
        String headImg = SpSir.getInstance().getHeadImg();
        ImageLoader.getInstance().loadImage(getActivity(), headImg, ivMineHead);
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
    public void onLogoutSuccess() {
        Logger.d("成功退出");
    }
}
