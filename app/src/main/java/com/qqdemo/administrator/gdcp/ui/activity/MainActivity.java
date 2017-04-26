package com.qqdemo.administrator.gdcp.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eccyan.widget.SpinningTabStrip;
import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.ui.fragment.IndexFragment;
import com.qqdemo.administrator.gdcp.widget.NestedScrollViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.qqdemo.administrator.gdcp.model.User.username;

/**
 * Created by Administrator on 2017/4/9.
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.iv_logout)
    ImageView mIvLogout;
    @BindView(R.id.btn_login)
    LinearLayout mBtnLogin;
    @BindView(R.id.iv_account)
    ImageView mIvAccount;
    @BindView(R.id.btn_account)
    LinearLayout mBtnAccount;
    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.tabs)
    SpinningTabStrip mTabs;
    @BindView(R.id.pager)
    NestedScrollViewPager mPager;
    private String titles[] = {"首页", "计算机工程学院","土木工程学院","汽车与工程机械学院","运输管理学院",
            "轨道交通学院","海事学院","商贸学院","电子与通信工程学院","机电工程学院"};


    boolean isExit = false;
    private Handler mHandler;
    private List<Fragment> mMListViews;
    TextView mTvUsername;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            return new IndexFragment();
        }
    }

    @Override
    protected void init() {
        super.init();
        Glide.with(this).load(R.mipmap.lolgo1).into(mIvLogo);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                isExit = false;
            }
        };

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(myPagerAdapter);
        mTabs.setViewPager(mPager);


        Glide.with(this).load(R.mipmap.logout1).into(mIvLogout);
        Glide.with(this).load(R.mipmap.account).into(mIvAccount);

        View headerLayout = mNavView.getHeaderView(0);
        mTvUsername = (TextView) headerLayout.findViewById(R.id.tv_username);
        mTvUsername.setText(username);
        mNavView.setNavigationItemSelectedListener(this);


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_item1:
                goTo(PerformanceActivity.class, false);
                break;
            case R.id.nav_item2:
                goTo(ScheduleActivity.class, false);
                break;
            case R.id.nav_item3:
                goTo(ComputerActivity.class, false);
                break;
            case R.id.nav_item4:

                break;
            case R.id.nav_item5:
                goTo(ChangePwdActivity.class, false);

                break;

        }


        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {

            exit();
        }
    }

    private void exit() {

        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }


    @OnClick({R.id.btn_account, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_account:
                goTo(LoginActivity.class, true);
                break;
            case R.id.btn_login:
                System.exit(0);
                break;
        }
    }


}
