package com.qqdemo.administrator.gdcp.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */

public class SchedulePageAdapter extends FragmentPagerAdapter {
    private List<Fragment> mListFragment;
    public SchedulePageAdapter(FragmentManager fm , List<Fragment> mList) {
        super(fm);
        this.mListFragment = mList;
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mListFragment.size();
    }
}
