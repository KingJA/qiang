package com.kingja.qiang.ui;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.kingja.popwindowsir.BasePop;
import com.kingja.popwindowsir.PopConfig;
import com.kingja.qiang.R;
import com.kingja.qiang.adapter.PriceAdapter;

import java.util.Arrays;

/**
 * Description:TODO
 * Create Time:2018/3/22 14:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CityPop extends BasePop {

    public CityPop(Context context) {
        super(context);
    }

    public CityPop(Context context, PopConfig popConfig) {
        super(context, popConfig);
    }

    @Override
    protected void initPop() {

    }

    @Override
    protected void initView(View contentView) {
        GridView gv_price = contentView.findViewById(R.id.gv_price);
    }

    @Override
    protected View getLayoutView() {
        return View.inflate(context, R.layout.pop_price, null);
    }
}
