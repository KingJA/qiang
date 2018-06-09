package com.kingja.qiang.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.kingja.qiang.R;
import com.kingja.qiang.base.BaseTitleActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.modify_nickname.ModifyNicknameActivity;
import com.kingja.qiang.util.GoUtil;
import com.kingja.qiang.util.ToastUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Description:TODO
 * Create Time:2018/2/26 16:07
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@RuntimePermissions
public class PersonalActivity extends BaseTitleActivity {
    @BindView(R.id.iv_personal_head)
    ImageView ivPersonalHead;
    @BindView(R.id.rl_personal_head)
    RelativeLayout rlPersonalHead;
    @BindView(R.id.rl_personal_nickanme)
    RelativeLayout rlPersonalNickanme;
    private static final int REQUEST_CODE_CHOOSE = 0;

    @OnClick({R.id.rl_personal_head, R.id.rl_personal_nickanme})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_personal_head:
                PersonalActivityPermissionsDispatcher.selectPhotoWithPermissionCheck(this);
                break;
            case R.id.rl_personal_nickanme:
                GoUtil.goActivity(this, ModifyNicknameActivity.class);
                break;
            default:
                break;
        }

    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE})
    public void selectPhoto() {
        Matisse.from(this)
                .choose(MimeType.allOf())
                .countable(true)
//                .capture(true)
                .theme(R.style.PhotoTheme)//主题  暗色主题 R.style.Matisse_Dracula
                .maxSelectable(1) // 图片选择的最多数量
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
    }

    List<Uri> mSelected;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PersonalActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE})
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("需要相机权限")
                .setPositiveButton("确定", (dialog, button) -> request.proceed())
                .setNegativeButton("取消", (dialog, button) -> request.cancel())
                .show();
    }

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE})
    void showDeniedForCamera() {
        ToastUtil.showText("OnPermissionDenied");
    }

    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE})
    void showNeverAskForCamera() {
        ToastUtil.showText("OnNeverAskAgain");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Glide.with(this)
                    .load(mSelected.get(0))
                    .centerCrop()
                    .crossFade()
                    .into(ivPersonalHead);
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "个人信息";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_personal;
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
