package com.kingja.qiang.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.page.visitor.list.Visitor;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/7/12 13:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class VisitorTabAdapter extends BaseRvAdaper<Visitor> {
    public VisitorTabAdapter(Context context, List<Visitor> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new VisitorTabViewHolder(v) ;
    }

    @Override
    protected int getItemView() {
        return R.layout.item_visitor_tab;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, Visitor visitor, int position) {
        final VisitorTabViewHolder holder = (VisitorTabViewHolder) baseHolder;
        holder.tv_visitor_tab.setText(visitor.getName());
        holder.iv_tab_sel.setVisibility(visitor.isSelected()?View.VISIBLE:View.GONE);
        holder.tv_visitor_tab.setBackgroundResource(visitor.isSelected()?R.drawable.shape_visitor_sel:R.drawable.shape_visitor_nor);
        holder.tv_visitor_tab.setTextColor(visitor.isSelected()? ContextCompat.getColor(context,R.color.red_hi): ContextCompat.getColor(context,R.color.c_6));
    }
    class VisitorTabViewHolder extends ViewHolder {
        TextView tv_visitor_tab;
        ImageView iv_tab_sel;

        VisitorTabViewHolder(View itemView) {
            super(itemView);
            tv_visitor_tab = itemView.findViewById(R.id.tv_visitor_tab);
            iv_tab_sel =  itemView.findViewById(R.id.iv_tab_sel);
        }
    }

}
