package com.kingja.qiang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.qiang.R;

import java.util.List;

import cn.iwgang.countdownview.CountdownView;

/**
 * Description:TODO
 * Create Time:2018/1/22 16:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DirectgoAdapter extends BaseLvAdapter<String> {
    public DirectgoAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_direct, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.cdv_direct.start(24 * 60 * 60 * 1000); // Millisecond
        return convertView;
    }

    @Override
    public int getCount() {
        return 20;
    }


    public class ViewHolder {
        public final View root;
        public CountdownView cdv_direct;

        public ViewHolder(View root) {
            this.root = root;
            cdv_direct = root.findViewById(R.id.cdv_direct);
        }
    }
}
