package com.kingja.qiang;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.qiang.base.BaseActivity;
import com.kingja.qiang.constant.NavConstant;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.util.FragmentUtil;
import com.kingja.qiang.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_nav_home)
    ImageView ivNavHigo;
    @BindView(R.id.tv_nav_home)
    TextView tvNavHigo;
    @BindView(R.id.ll_nav_home)
    LinearLayout llNavHigo;
    @BindView(R.id.iv_nav_order)
    ImageView ivNavOrder;
    @BindView(R.id.tv_nav_order)
    TextView tvNavJourney;
    @BindView(R.id.ll_nav_order)
    LinearLayout llNavOrder;
    @BindView(R.id.iv_nav_mine)
    ImageView ivNavMine;
    @BindView(R.id.tv_nav_mine)
    TextView tvNavMine;
    @BindView(R.id.ll_nav_mine)
    LinearLayout llNavMine;

    private Fragment mCurrentFragment;
    private int nCurrentPosition = -1;

    @Override
    public void initData() {

    }

    @Override
    public void initVariable() {
        MainActivityPermissionsDispatcher.checkPermissionsWithPermissionCheck(this);
        checkPermissions();

    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS, Manifest.permission
            .READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION})
    public void checkPermissions() {

    }

    @Override
    public View getContentId() {
        return View.inflate(this, R.layout.activity_main, null);
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initView() {
        mCurrentFragment = FragmentUtil.getFragment(NavConstant.NAV_XIGO);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, mCurrentFragment).commit();
    }


    @Override
    protected void initNet() {

    }

    @OnClick({R.id.ll_nav_home, R.id.ll_nav_order, R.id.ll_nav_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_nav_home:
                selectTab(NavConstant.NAV_XIGO);
                break;
            case R.id.ll_nav_order:
                selectTab(NavConstant.NAV_ORDER);
                break;
            case R.id.ll_nav_mine:
                selectTab(NavConstant.NAV_MINE);
                break;
            default:
                break;
        }
    }

    //防止Fragment重生重叠
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    private long mLastTime;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastTime < 500) {
            finish();
        } else {
            ToastUtil.showText("连续点击退出");
            mLastTime = currentTime;

        }
    }

    private void setStatus(int index) {
        resetBottom();
        switch (index) {
            case NavConstant.NAV_XIGO:
                ivNavHigo.setBackgroundResource(R.mipmap.ic_home_sel);
                tvNavHigo.setTextColor(getResources().getColor(R.color.red_hi));
                break;
            case NavConstant.NAV_ORDER:
                ivNavOrder.setBackgroundResource(R.mipmap.ic_order_sel);
                tvNavJourney.setTextColor(getResources().getColor(R.color.red_hi));
                break;
            case NavConstant.NAV_MINE:
                ivNavMine.setBackgroundResource(R.mipmap.ic_mine_sel);
                tvNavMine.setTextColor(getResources().getColor(R.color.red_hi));
                break;
            default:
                break;

        }
    }

    private void resetBottom() {
        ivNavHigo.setBackgroundResource(R.mipmap.ic_home_nor);
        ivNavOrder.setBackgroundResource(R.mipmap.ic_order_nor);
        ivNavMine.setBackgroundResource(R.mipmap.ic_mine_nor);

        tvNavHigo.setTextColor(getResources().getColor(R.color.gray_hi));
        tvNavJourney.setTextColor(getResources().getColor(R.color.gray_hi));
        tvNavMine.setTextColor(getResources().getColor(R.color.gray_hi));
    }

    private void selectTab(int position) {
        if (position == nCurrentPosition) {
            return;
        }
        mCurrentFragment = FragmentUtil.switchFragment(getSupportFragmentManager(), mCurrentFragment, FragmentUtil
                .getFragment(position));
        nCurrentPosition = position;
        setStatus(position);
    }
}
