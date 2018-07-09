package com.kingja.qiang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kingja.qiang.R;
import com.kingja.supershapeview.core.SuperManager;
import com.kingja.supershapeview.view.SuperShapeTextView;

import java.util.List;
import java.util.Random;

/**
 * Description:TODO
 * Create Time:2018/1/22 16:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DiscountCenterAdapter extends BaseLvAdapter<String> {
    public DiscountCenterAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_discount_center, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SuperManager superManager = viewHolder.sstv_operate.getSuperManager();
        int operate = new Random().nextInt(2);
        if (operate == 0) {
            viewHolder.sstv_operate.setText("立即领取");
            viewHolder.sstv_operate.setTextColor(context.getResources().getColor(R.color.white_hi));
            viewHolder.iv_discount_stramp.setVisibility(View.GONE);
            superManager.setSolidColor(context.getResources().getColor(R.color.red_hi));
        } else {
            viewHolder.sstv_operate.setText("去使用");
            viewHolder.iv_discount_stramp.setVisibility(View.VISIBLE);
            viewHolder.sstv_operate.setTextColor(context.getResources().getColor(R.color.red_hi));
            superManager.setSolidColor(context.getResources().getColor(R.color.white_hi));
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return 27;
    }

    public class ViewHolder {
        public final View root;
        public SuperShapeTextView sstv_operate;
        public ImageView iv_discount_stramp;

        public ViewHolder(View root) {
            this.root = root;
            sstv_operate = root.findViewById(R.id.sstv_operate);
            iv_discount_stramp = root.findViewById(R.id.iv_discount_stramp);
        }
    }
}
