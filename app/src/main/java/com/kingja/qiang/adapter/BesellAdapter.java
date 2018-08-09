package com.kingja.qiang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.imgaeloader.ImageLoader;
import com.kingja.qiang.model.entiy.Ticket;
import com.kingja.qiang.util.DateUtil;
import com.kingja.supershapeview.view.SuperShapeTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/1/22 16:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BesellAdapter extends BaseLvAdapter<Ticket> {
    public BesellAdapter(Context context, List<Ticket> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_ticket_besell, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int[] deadlineDate = DateUtil.getDeadlineDate(list.get(position).getStartTime());
        viewHolder.stv_date_day.setText(String.valueOf(deadlineDate[0]));
        viewHolder.stv_date_hour.setText(String.valueOf(deadlineDate[1]));
        viewHolder.stv_date_min.setText(String.valueOf(deadlineDate[2]));
        viewHolder.tv_ticket_area.setText(list.get(position).getAreaText());
        viewHolder.tv_ticket_title.setText(list.get(position).getTicketName());
        viewHolder.tv_ticket_totalCount.setText(String.valueOf(list.get(position).getTotalCount()));
        viewHolder.dtv_ticket_marketPrice.setText(String.valueOf((int)list.get(position).getMarketPrice()));
        viewHolder.dtv_ticket_buyPrice.setText(String.valueOf((int)list.get(position).getBuyPrice()));
        viewHolder.tv_ticket_date.setText(list.get(position).getUseDate());
        ImageLoader.getInstance().loadImage(context, list.get(position).getHeadImg(), R.mipmap.ic_placeholder,
                viewHolder.iv_ticket_img);
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    public class ViewHolder {
        public final View root;
        SuperShapeTextView stv_date_day;
        SuperShapeTextView stv_date_hour;
        SuperShapeTextView stv_date_min;
        TextView tv_ticket_area;
        TextView tv_ticket_title;
        TextView tv_ticket_totalCount;
        TextView tv_ticket_date;
        TextView dtv_ticket_marketPrice;
        TextView dtv_ticket_buyPrice;
        ImageView iv_ticket_img;

        public ViewHolder(View root) {
            this.root = root;
            stv_date_day = root.findViewById(R.id.stv_date_day);
            stv_date_hour = root.findViewById(R.id.stv_date_hour);
            stv_date_min = root.findViewById(R.id.stv_date_min);
            tv_ticket_area = root.findViewById(R.id.tv_ticket_area);
            tv_ticket_title = root.findViewById(R.id.tv_ticket_title);
            tv_ticket_totalCount = root.findViewById(R.id.tv_ticket_totalCount);
            tv_ticket_date = root.findViewById(R.id.tv_ticket_date);
            dtv_ticket_marketPrice = root.findViewById(R.id.dtv_ticket_marketPrice);
            dtv_ticket_buyPrice = root.findViewById(R.id.dtv_ticket_buyPrice);
            iv_ticket_img = root.findViewById(R.id.iv_ticket_img);
        }
    }
}
