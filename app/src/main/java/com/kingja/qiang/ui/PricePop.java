package com.kingja.qiang.ui;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.kingja.qiang.R;
import com.kingja.qiang.adapter.PriceAdapter;
import com.kingja.popwindowsir.BasePop;
import com.kingja.popwindowsir.PopConfig;

import java.util.Arrays;

/**
 * Description:TODO
 * Create Time:2018/3/22 14:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PricePop extends BasePop {

    public PricePop(Context context) {
        super(context);
    }

    public PricePop(Context context, PopConfig popConfig) {
        super(context, popConfig);
    }

    @Override
    protected void initPop() {

    }

    @Override
    protected void initView(View contentView) {
        GridView gv_price = contentView.findViewById(R.id.gv_price);
        final PriceAdapter priceAdapter = new PriceAdapter(context, Arrays.asList("¥150以下", "¥150-200", "¥200-250",
                "¥250-300", "¥350-550", "¥550以上"));
        gv_price.setAdapter(priceAdapter);
        gv_price.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                priceAdapter.selectPosition(position);
            }
        });
    }

    @Override
    protected View getLayoutView() {
        return View.inflate(context, R.layout.pop_price, null);
    }
}
