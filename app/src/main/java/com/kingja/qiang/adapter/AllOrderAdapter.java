package com.kingja.qiang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.page.order.Order;
import com.kingja.supershapeview.view.SuperShapeTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/1/22 16:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AllOrderAdapter extends BaseLvAdapter<Order> {
    public AllOrderAdapter(Context context, List<Order> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_order_all, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_order_status.setVisibility(position % 3 == 0 ? View.GONE : View.VISIBLE);
        viewHolder.stv_order_detail.setVisibility(position % 3 == 0 ? View.GONE : View.VISIBLE);
        viewHolder.iv_order_stamp.setVisibility(position % 3 == 0 ? View.VISIBLE : View.GONE);
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public class ViewHolder {
        public final View root;
        TextView tv_order_status;
         SuperShapeTextView stv_order_detail;
         ImageView iv_order_stamp;

        public ViewHolder(View root) {
            this.root = root;
            tv_order_status = root.findViewById(R.id.tv_order_status);
            stv_order_detail = root.findViewById(R.id.stv_order_detail);
            iv_order_stamp = root.findViewById(R.id.iv_order_stamp);
        }
    }
}
