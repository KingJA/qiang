package com.kingja.qiang.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kingja.qiang.fragment.TicketPriceFragment;

/**
 * Description:TODO
 * Create Time:2018/1/22 16:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TicketPricePageAdapter extends FragmentPagerAdapter {
    private String[] titles;

    public TicketPricePageAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return TicketPriceFragment.getInstance(titles[position]);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
