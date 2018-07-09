package com.kingja.qiang.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.kingja.qiang.R;
import com.kingja.qiang.constant.NavConstant;
import com.kingja.qiang.fragment.XigouFragment;
import com.kingja.qiang.fragment.OrderFragment;
import com.kingja.qiang.page.mine.MineFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：TODO
 * Create Time：2017/3/2 15:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FragmentUtil {
    private static Map<Integer, Fragment> fragmentMap = new HashMap<>();

    public static Fragment switchFragment(FragmentManager fragmentManager, Fragment currentFragment, Fragment
            newFragment) {
        FragmentTransaction mTransaction = fragmentManager.beginTransaction();
        if (!newFragment.isAdded()) {
            mTransaction.hide(currentFragment).add(R.id.fl_main, newFragment).commit();
        } else {
            mTransaction.hide(currentFragment).show(newFragment).commit();
        }
        return newFragment;
    }


    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentMap.get(position);
        if (fragment != null) {
            return fragment;
        } else {
            switch (position) {
                case NavConstant.NAV_XIGO:
                    fragment = new XigouFragment();
                    break;
                case NavConstant.NAV_ORDER:
                    fragment = new OrderFragment();
                    break;
                case NavConstant.NAV_MINE:
                    fragment = new MineFragment();
                    break;
                default:
                    fragment = new XigouFragment();
                    break;
            }
            fragmentMap.put(position, fragment);
            return fragment;
        }
    }
}
