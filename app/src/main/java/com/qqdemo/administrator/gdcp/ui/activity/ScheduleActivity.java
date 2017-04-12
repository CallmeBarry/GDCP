package com.qqdemo.administrator.gdcp.ui.activity;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.adapter.SchedulePageAdapter;
import com.qqdemo.administrator.gdcp.factroy.FragmentFactory;
import com.qqdemo.administrator.gdcp.presenter.SchedulePresenter;
import com.qqdemo.administrator.gdcp.presenter.impl.SchedulePresenterImpl;
import com.qqdemo.administrator.gdcp.view.ScheduleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScheduleActivity extends BaseActivity implements ScheduleView {

    private static final String TAG = "ScheduleActivity";
    @BindView(R.id.fragment_container)
    ViewPager mFragmentContainer;
    @BindView(R.id.NavigationTabStrip)
    NavigationTabStrip mNavigationTabStrip;
    private SchedulePresenter mSchedulePresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_schedule;
    }

    @Override
    protected void init() {
        super.init();


        mSchedulePresenter = new SchedulePresenterImpl(this);


    }


    @Override
    public void initViewPager() {
        List<Fragment> mListViews = new ArrayList<>();
        mListViews.add(FragmentFactory.getInstance().getScheduleFragment1(mSchedulePresenter.getSchedule1()));
        mListViews.add(FragmentFactory.getInstance().getScheduleFragment2(mSchedulePresenter.getSchedule2()));
        mListViews.add(FragmentFactory.getInstance().getScheduleFragment3(mSchedulePresenter.getSchedule3()));
        SchedulePageAdapter schedulePageAdapter = new SchedulePageAdapter(getSupportFragmentManager(),mListViews);
        mFragmentContainer.setAdapter(schedulePageAdapter);
        mNavigationTabStrip.setViewPager(mFragmentContainer, 0);
    }
}
