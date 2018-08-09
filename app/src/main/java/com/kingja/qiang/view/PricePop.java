package com.kingja.qiang.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.kingja.popwindowsir.BasePop;
import com.kingja.popwindowsir.PopConfig;
import com.kingja.qiang.R;

/**
 * Description:TODO
 * Create Time:2018/3/22 14:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PricePop extends BasePop {

    private OnDiscountRateSelectedLintener onDiscountRateSelectedLintener;
    private RangeSeekBar rsb_discountRate;
    private String discountRate = "0.1,0.9";
    private ImageView iv_one_yuan;
    private TextView tv_leftValue;
    private TextView tv_rightValue;

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
        rsb_discountRate = contentView.findViewById(R.id.rsb_discountRate);
        tv_leftValue = contentView.findViewById(R.id.tv_leftValue);
        tv_rightValue = contentView.findViewById(R.id.tv_rightValue);
        TextView tv_confirm = contentView.findViewById(R.id.tv_confirm);
        TextView tv_cancel = contentView.findViewById(R.id.tv_cancel);
        LinearLayout ll_one_yuan = contentView.findViewById(R.id.ll_one_yuan);
        iv_one_yuan = contentView.findViewById(R.id.iv_one_yuan);
        rsb_discountRate.setValue(1, 9);
        rsb_discountRate.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                tv_leftValue.setText((int) leftValue + "");
                tv_rightValue.setText((int) rightValue + "");
                discountRate = "0." +(int) leftValue + ",0." + (int) rightValue;
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
        ll_one_yuan.setOnClickListener(v -> {
            if (onDiscountRateSelectedLintener != null) {
                iv_one_yuan.setBackgroundResource(R.mipmap.ic_oneyuan_sel);
                resetDiscountDate();
                onDiscountRateSelectedLintener.onDiscountRateSelected("0.01");
            }
            dismiss();

        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDiscountRateSelectedLintener != null) {
                    iv_one_yuan.setBackgroundResource(R.mipmap.ic_oneyuan_nor);
                    onDiscountRateSelectedLintener.onDiscountRateSelected(discountRate);
                }
                dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_one_yuan.setBackgroundResource(R.mipmap.ic_oneyuan_nor);
                resetDiscountDate();
                if (onDiscountRateSelectedLintener != null) {
                    onDiscountRateSelectedLintener.onDiscountRateSelected("");
                }
                dismiss();
            }
        });
    }

    private void resetDiscountDate() {
        rsb_discountRate.setValue(1, 9);
        discountRate = "0.1,0.9";
        tv_leftValue.setText("1");
        tv_rightValue.setText("9");
    }

    @Override
    protected View getLayoutView() {
        return View.inflate(context, R.layout.pop_price, null);
    }

    public interface OnDiscountRateSelectedLintener {
        void onDiscountRateSelected(String discountRate);
    }

    public void setOnDiscountRateSelectedLintener(OnDiscountRateSelectedLintener onDiscountRateSelectedLintener) {
        this.onDiscountRateSelectedLintener = onDiscountRateSelectedLintener;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 1.0f;
        ((Activity) context).getWindow().setAttributes(lp);
    }
}
