package com.kingja.qiang.callback;


import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.kingja.qiang.R;
import com.kingja.loadsir.callback.Callback;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class EmptyOrderCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_empty_order;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        (view.findViewById(R.id.stv_go_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), "It's your gift! :p", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }
}
