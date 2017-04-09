package com.qqdemo.administrator.gdcp.presenter.impl;


import com.qqdemo.administrator.gdcp.presenter.SplashPresenter;
import com.qqdemo.administrator.gdcp.view.SplashView;

/**
 * Created by Administrator on 2017/4/9.
 */

public class SplashPresenterImpl implements SplashPresenter {
    private SplashView mSplashView;
    public SplashPresenterImpl(SplashView view){
        mSplashView=view;
    }

    @Override
    public void checkLoginStatus() {
        if(isLoggedIn()){
            mSplashView.onLoggedIn();
        }else{
            mSplashView.onNotLogged();
        }
    }

    public boolean isLoggedIn() {
        return false;
    }
}
