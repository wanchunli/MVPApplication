package com.wan.grace.mvpapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager适配器
 * CustomViewPagerAdapter
 * author wanchun
 * email 1596900283@qq.com
 * create 2018/3/5 14:28
 */
public class CustomViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();

    public static final int TAB_PLATFORM = 0;
    public static final int TAB_MESSAGE = 1;
    public static final int TAB_TOOLS = 2;
    public static final int TAB_USER = 3;

    public CustomViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}
