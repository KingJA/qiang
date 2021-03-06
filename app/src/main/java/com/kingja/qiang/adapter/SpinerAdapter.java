package com.kingja.qiang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.qiang.R;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/1/22 16:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SpinerAdapter extends BaseLvAdapter<String> {
    public SpinerAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_spiner, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_spiner.setText(list.get(position));
        if (position == 2) {
            viewHolder.iv_spiner_arrow.setVisibility(View.VISIBLE);
        }else{
            viewHolder.iv_spiner_arrow.setVisibility(View.GONE);
        }
        return convertView;
    }


    public class ViewHolder {
        public final View root;
        public TextView tv_spiner;
        public ImageView iv_spiner_arrow;

        public ViewHolder(View root) {
            this.root = root;
            tv_spiner = root.findViewById(R.id.tv_spiner);
            iv_spiner_arrow = root.findViewById(R.id.iv_spiner_arrow);
        }
    }
}
