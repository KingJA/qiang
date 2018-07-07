package com.kingja.qiang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.imgaeloader.ImageLoader;
import com.kingja.qiang.page.order.Order;
import com.kingja.qiang.page.order.orderdetail.OrderDetailActivity;
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
        viewHolder.tv_order_orderno.setText(list.get(position).getOrderNo());
        viewHolder.tv_order_title.setText(list.get(position).getSubject());
        viewHolder.tv_order_quantity.setText(String.valueOf(list.get(position).getQuantity()));
        viewHolder.tv_order_area.setText(list.get(position).getAreaText());
        viewHolder.tv_order_payamount.setText(String.valueOf(list.get(position).getPayamount()));
        viewHolder.tv_order_date.setText(list.get(position).getVisitDate());
        ImageLoader.getInstance().loadImage(context, list.get(position).getHeadimg(),R.mipmap.ic_placeholder, viewHolder.iv_order_img);

        viewHolder.iv_order_stamp.setVisibility(list.get(position).getStatus() == 1 ? View.GONE : View.VISIBLE);
        viewHolder.stv_order_detail.setVisibility(list.get(position).getStatus()  == 1 ? View.VISIBLE : View.GONE);
        viewHolder.tv_order_status.setVisibility(list.get(position).getStatus()  == 1 ? View.VISIBLE : View.GONE);
        viewHolder.stv_order_detail.setOnClickListener(v -> {
            OrderDetailActivity.goActivity(context,list.get(position).getId());

        });
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public class ViewHolder {
        public final View root;
        TextView tv_order_orderno;
        TextView tv_order_area;
        TextView tv_order_title;
        TextView tv_order_payamount;
        TextView tv_order_date;
        TextView tv_order_status;
        TextView tv_order_quantity;
        SuperShapeTextView stv_order_detail;
        ImageView iv_order_stamp;
        ImageView iv_order_img;

        public ViewHolder(View root) {
            this.root = root;
            tv_order_orderno = root.findViewById(R.id.tv_order_orderno);
            tv_order_area = root.findViewById(R.id.tv_order_area);
            tv_order_title = root.findViewById(R.id.tv_order_title);
            tv_order_payamount = root.findViewById(R.id.tv_order_payamount);
            tv_order_date = root.findViewById(R.id.tv_order_date);
            tv_order_quantity = root.findViewById(R.id.tv_order_quantity);
            tv_order_status = root.findViewById(R.id.tv_order_status);
            stv_order_detail = root.findViewById(R.id.stv_order_detail);
            iv_order_stamp = root.findViewById(R.id.iv_order_stamp);
            iv_order_img = root.findViewById(R.id.iv_order_img);
        }
    }
}
