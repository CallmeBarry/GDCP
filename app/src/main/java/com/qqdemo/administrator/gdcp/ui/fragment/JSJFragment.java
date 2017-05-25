package com.qqdemo.administrator.gdcp.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.adapter.DateListAdapter;
import com.qqdemo.administrator.gdcp.presenter.JSJFragmentPresenter;
import com.qqdemo.administrator.gdcp.presenter.impl.JSJFragmentPresenterImpl;
import com.qqdemo.administrator.gdcp.view.JSJFragmentView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/26.
 */
public class JSJFragment extends BaseFragment implements JSJFragmentView {
    @BindView(R.id.recycler_list)
    RecyclerView mRecyclerLineList;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_jsj;
    }


    @Override
    protected void init() {
        super.init();
        JSJFragmentPresenter JSJFragmentPresenter = new JSJFragmentPresenterImpl(this);
        JSJFragmentPresenter.onloadDate();
    }

    @Override
    public void onLoadDateFailed() {

    }

    @Override
    public void onLoadDateSuccess(List<Map<String, Object>> dateslist) {
        mRecyclerLineList.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecyclerLineList.setHasFixedSize(true);
        mRecyclerLineList.setNestedScrollingEnabled(false);
        mRecyclerLineList.setAdapter(new DateListAdapter(dateslist));

    }

}
