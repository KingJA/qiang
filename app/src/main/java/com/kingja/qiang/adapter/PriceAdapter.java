package com.kingja.qiang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.qiang.R;
import com.kingja.supershapeview.core.SuperManager;
import com.kingja.supershapeview.view.SuperShapeTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/3/22 14:47
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PriceAdapter extends BaseLvAdapter<String> {
    public PriceAdapter(Context context, List<String> list) {
        super(context, list);
    }
    private int selectPosition=-1;

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_price, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.stv_price.setText(list.get(position));
        SuperManager superManager = viewHolder.stv_price.getSuperManager();
        if (selectPosition == position) {
            superManager.setSolidColor(context.getResources().getColor(R.color.red_hi));
            superManager.setStrokeColor(context.getResources().getColor(R.color.red_hi));
            viewHolder.stv_price.setTextColor(context.getResources().getColor(R.color.white_hi));
        }else{
            superManager.setSolidColor(context.getResources().getColor(R.color.white_hi));
            superManager.setStrokeColor(context.getResources().getColor(R.color.gray_hi));
            viewHolder.stv_price.setTextColor(context.getResources().getColor(R.color.c_6));
        }
        return convertView;
    }

    public class ViewHolder {
        public final View root;
        public SuperShapeTextView stv_price;

        public ViewHolder(View root) {
            this.root = root;
            this.stv_price = root.findViewById(R.id.stv_price);
        }
    }

    public void selectPosition(int selectPosition) {
        this.selectPosition=selectPosition;
        notifyDataSetChanged();
    }
}