package com.kingja.qiang.callback;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.kingja.qiang.R;
import com.kingja.loadsir.callback.Callback;
import com.kingja.qiang.page.visitor.add.VisitorAddActivity;
import com.kingja.qiang.util.GoUtil;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class EmptyVisitorCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_empty_visitor;
    }
    @Override
    protected boolean onReloadEvent(Context context, View view) {
        (view.findViewById(R.id.stv_add_visitor)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoUtil.goActivity((Activity) context, VisitorAddActivity.class);
            }
        });
        return true;
    }
}
