package com.kingja.qiang.callback;


import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.kingja.qiang.R;
import com.kingja.qiang.page.login.LoginActivity;
import com.kingja.qiang.util.GoUtil;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class UnLoginCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_error_unlogin;
    }
    @Override
    protected boolean onReloadEvent(Context context, View view) {
        GoUtil.goActivity((Activity) context, LoginActivity.class);
        return true;
    }
}
