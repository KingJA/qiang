package com.kingja.qiang.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kingja.qiang.R;

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
}
