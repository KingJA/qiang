package com.kingja.qiang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.kingja.contactssir.Contacts;
import com.kingja.qiang.R;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/3/15 9:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsAdapter extends BaseLvAdapter<Contacts> implements SectionIndexer {
    public ContactsAdapter(Context context, List<Contacts> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_contacts, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getName());
        viewHolder.tv_number.setText(list.get(position).getNumber());
        viewHolder.tv_firstLetter.setText(list.get(position).getFirstLetter());
        int currentFirstLetter = getSectionForPosition(position);
        if (position == getPositionForSection(currentFirstLetter)) {
            viewHolder.tv_firstLetter.setVisibility(View.VISIBLE);
            viewHolder.tv_firstLetter.setText(list.get(position).getFirstLetter());
        } else {
            viewHolder.tv_firstLetter.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getFirstLetter();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).getFirstLetter().charAt(0);
    }

    public class ViewHolder {
        public TextView tv_firstLetter;
        public TextView tv_name;
        public TextView tv_number;
        public View root;

        public ViewHolder(View root) {
            this.root = root;
            tv_firstLetter = root.findViewById(R.id.tv_firstLetter);
            tv_name = root.findViewById(R.id.tv_name);
            tv_number = root.findViewById(R.id.tv_number);
        }
    }
}
