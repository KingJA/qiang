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

}
