package com.qqdemo.administrator.gdcp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;

import com.qqdemo.administrator.gdcp.factroy.FragmentFactory;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "MainPagerAdapter";
    private List<String> mTitles;

    public MainPagerAdapter(FragmentManager fm, List mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public Fragment getItem(int position) {

        return FragmentFactory.getInstance().getFragment(mTitles.get(position));
    }

    public void changeDate(List titles) {
        this.mTitles = titles;
        Log.i(TAG, "changeDate: " + titles);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}