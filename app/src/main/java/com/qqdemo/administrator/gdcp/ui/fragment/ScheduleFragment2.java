package com.qqdemo.administrator.gdcp.ui.fragment;

import android.annotation.SuppressLint;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.adapter.ScheduleAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/12.
 */
@SuppressLint("ValidFragment")
public class ScheduleFragment2 extends BaseFragment {
    @BindView(R.id.scrollable_panel)
    ScrollablePanel mScrollablePanel;
    List<List<String>> mLists;
    public ScheduleFragment2(List<List<String>> mLists) {
        super();
        this.mLists=mLists;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_schedule2;
    }

    @Override
    protected void init() {
        super.init();

        ScheduleAdapter mScheduleAdapter = new ScheduleAdapter(mLists);
        mScrollablePanel.setPanelAdapter(mScheduleAdapter);

    }


}
