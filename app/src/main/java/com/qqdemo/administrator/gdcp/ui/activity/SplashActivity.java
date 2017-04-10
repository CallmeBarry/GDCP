package com.qqdemo.administrator.gdcp.ui.activity;

import android.os.Handler;

import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.presenter.SplashPresenter;
import com.qqdemo.administrator.gdcp.presenter.impl.SplashPresenterImpl;
import com.qqdemo.administrator.gdcp.view.SplashView;

public class SplashActivity extends BaseActivity implements SplashView {

    private static final int DELAY = 2000;
    private Handler mHandler=new Handler();

    private SplashPresenter mSplashPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        super.init();
        mSplashPresenter=new SplashPresenterImpl(this);

        mSplashPresenter.checkLoginStatus();
    }

    @Override
    public void onLoggedIn() {
        navigateToMain();
    }

    @Override
    public void onNotLogged() {
        navigateToLogin();
    }

    //跳转到登陆界面
    private void navigateToLogin() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goTo(LoginActivity.class,true);
            }
        },DELAY);
    }

    //跳转到主界面
    private void navigateToMain() {
       goTo( MainActivity.class,true);
    }

}
