package com.kingja.qiang.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kingja.qiang.page.login.LoginActivity;

/**
 * Description:TODO
 * Create Time:2018/7/7 10:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoginChecker {

    public static void goActivity(Activity context, Class targetActivity) {
        if (TextUtils.isEmpty(SpSir.getInstance().getToken())) {
            DialogUtil.showConfirmDialog(context, "亲，您还未登录，是否马上登录", (dialog, which) -> {
                GoUtil.goActivity(context, LoginActivity.class);
            });

        }else{
            GoUtil.goActivity(context,targetActivity);
        }
    }

    public static boolean isLogin() {
        return !TextUtils.isEmpty(SpSir.getInstance().getToken());
    }
}
