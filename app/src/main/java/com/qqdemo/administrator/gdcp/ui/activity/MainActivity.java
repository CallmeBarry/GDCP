package com.qqdemo.administrator.gdcp.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.qqdemo.administrator.gdcp.R;

import butterknife.BindView;

import static com.qqdemo.administrator.gdcp.model.User.username;

/**
 * Created by Administrator on 2017/4/9.
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    TextView mTvUsername;


    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
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
            super.onBackPressed();
        }
    }
}
