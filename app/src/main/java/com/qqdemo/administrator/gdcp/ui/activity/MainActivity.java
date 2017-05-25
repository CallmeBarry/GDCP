package com.qqdemo.administrator.gdcp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.adapter.MainPagerAdapter;
import com.qqdemo.administrator.gdcp.model.MyEvent;
import com.qqdemo.administrator.gdcp.presenter.impl.SettingPresentImpl;
import com.qqdemo.administrator.gdcp.widget.NestedScrollViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.qqdemo.administrator.gdcp.model.User.username;

/**
 * Created by Administrator on 2017/4/9.
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String TAG = "MainActivity";
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
    @BindView(R.id.pager)
    NestedScrollViewPager mPager;
    @BindView(R.id.tabs)
    SlidingTabLayout mTabs;
    @BindView(R.id.more)
    ImageButton mMore;


    boolean isExit = false;
    private Handler mHandler;
    TextView mTvUsername;
    private SettingPresentImpl mSettingPresent;
    private List<Map<String, Object>> mMaps;
    private List mTitles;
    private MainPagerAdapter mMyPagerAdapter;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        Glide.with(this).load(R.mipmap.lolgo1).into(mIvLogo);
        EventBus.getDefault().register(this);
        initHandler();

        mSettingPresent = new SettingPresentImpl(getApplicationContext());

        mMaps = mSettingPresent.loadTittle();
        Log.i(TAG, "init: " + mMaps);
        mTitles = new ArrayList();
        for (Map<String, Object> map : mMaps) {
            if ((Boolean) map.get("key"))
                mTitles.add(map.get("tittle"));
        }
        mTitles.add(0, "首页");
        mMyPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mTitles);

        mPager.setAdapter(mMyPagerAdapter);
        mTabs.setViewPager(mPager);


        Glide.with(this).load(R.mipmap.logout1).into(mIvLogout);
        Glide.with(this).load(R.mipmap.account).into(mIvAccount);

        View headerLayout = mNavView.getHeaderView(0);
        mTvUsername = (TextView) headerLayout.findViewById(R.id.tv_username);
        mTvUsername.setText(username);
        mNavView.setNavigationItemSelectedListener(this);


    }

    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                isExit = false;
            }
        };
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
                showDailog();
//                goTo(MoneryActivity.class, false);
                break;
            case R.id.nav_item5:
                goTo(ChangePwdActivity.class, false);

                break;

        }


        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    private void showDailog() {
        final View inflate = LayoutInflater.from(this).inflate(R.layout.dailog_money, null);
        new AlertDialog.Builder(this)
                .setView(inflate)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText edpwd = (EditText) inflate.findViewById(R.id.ed_pwd);
                        String pwd = edpwd.getText().toString();
                        mSettingPresent.checkpwd(pwd);
                    }
                })
                .show();
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


    @OnClick({R.id.btn_account, R.id.btn_login, R.id.more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_account:
                goTo(LoginActivity.class, true);
                break;
            case R.id.btn_login:
                System.exit(0);
                break;
            case R.id.more:
                goTo(SettingActivity.class, false);
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyEvent event) {
        mMaps = event.getMaps();
        mTitles.clear();
        for (Map<String, Object> map : mMaps) {
            if ((Boolean) map.get("key"))
                mTitles.add(map.get("tittle"));
        }
        mTitles.add(0, "首页");
        Log.i(TAG, "onMessageEvent: " + mMaps);
        mMyPagerAdapter.changeDate(mTitles);
        mPager.setAdapter(mMyPagerAdapter);
        mTabs.notifyDataSetChanged();
        mTabs.setViewPager(mPager);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
