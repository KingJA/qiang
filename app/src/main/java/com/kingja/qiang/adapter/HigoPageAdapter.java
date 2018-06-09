package com.kingja.qiang.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.qiang.R;

/**
 * Description:TODO
 * Create Time:2018/1/22 16:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class HigoPageAdapter extends FragmentPagerAdapter {
    private Context context;
    private Fragment[] fragments;
    private String[] titles;
    private int[] icons;

    public HigoPageAdapter(Context context, FragmentManager fm, Fragment[] fragments, String[] titles, int[] icons) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
        this.icons = icons;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        TextView tv_tab = (TextView) view.findViewById(R.id.tv_tab);
        tv_tab.setText(titles[position]);
        ImageView iv_tab = (ImageView) view.findViewById(R.id.iv_tab);
        iv_tab.setImageResource(icons[position]);
        return view;
    }
}
