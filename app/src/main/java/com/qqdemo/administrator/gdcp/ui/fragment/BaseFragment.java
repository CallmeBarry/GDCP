package com.qqdemo.administrator.gdcp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/28.
 */

public abstract class BaseFragment extends Fragment {
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView==null){
            mView = LayoutInflater.from(getContext()).inflate(getLayoutResId(), container, false);
            ButterKnife.bind(this, mView);
            init();
        }

        return mView;
    }

    protected void init(){
    };

    public abstract int getLayoutResId();

    void goTo(Class clazz,boolean isFinish){
        Intent intent=new Intent(getActivity(),clazz);
        startActivity(intent);
        if(isFinish){
            getActivity().finish();
        }

    }

}
