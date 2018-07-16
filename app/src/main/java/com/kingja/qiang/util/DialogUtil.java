package com.kingja.qiang.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kingja.qiang.R;
import com.kingja.qiang.page.login.LoginActivity;

/**
 * Description:TODO
 * Create Time:2018/7/7 10:39
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DialogUtil {
    private static String CONFIRM="确定";

    public static void showConfirmDialog(Context context,String message, MaterialDialog.SingleButtonCallback callback) {
        new MaterialDialog.Builder(context)
                .content(message)
                .positiveText(CONFIRM)
                .onPositive(callback)
                .show();
    }

    public static void showDoubleDialog(Context context,String message, MaterialDialog.SingleButtonCallback callback) {
        new MaterialDialog.Builder(context)
                .content(message)
                .positiveText("确认")
                .negativeText("取消")
                .positiveColor(ContextCompat.getColor(context, R.color.gray_hi))
                .onPositive(callback)
                .show();
    }

    public static void showLoginActivity(Activity context) {
        DialogUtil.showConfirmDialog(context, "亲，您还未登录，是否马上登录", (dialog, which) -> {
            GoUtil.goActivity(context, LoginActivity.class);
        });
    }
}
