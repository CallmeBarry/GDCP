package com.qqdemo.administrator.gdcp.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.adapter.ComputerListAdapter;
import com.qqdemo.administrator.gdcp.model.MyEvent;
import com.qqdemo.administrator.gdcp.presenter.ComputerPresent;
import com.qqdemo.administrator.gdcp.presenter.impl.ComputerPresentImpl;
import com.qqdemo.administrator.gdcp.utils.ThreadUtils;
import com.qqdemo.administrator.gdcp.view.ComputerView;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/14.
 */
public class ComputerActivity extends BaseActivity implements ComputerView, View.OnClickListener {

    private static final String TAG = "ComputerActivity";
    @BindView(R.id.rv_computer)
    RecyclerView mRvComputer;
    @BindView(R.id.bar_title)
    TextView mBarTitle;
    @BindView(R.id.back)
    ImageView mBack;

    private ResideMenu mResideMenu;
    private ComputerPresent mComputerPresent;
    private String[] mTitles = new String[]{"公共机房", "计算机学院"};
    int mIcon[] = {R.mipmap.computerarrange, R.mipmap.computerarrange};
    private ResideMenuItem mItem1;
    private ResideMenuItem mItem2;
    private ResideMenuItem[] mItem;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_computer;
    }


    @Subscribe
    public void onEventMainThread(final MyEvent event) {
        final MyEvent event1 = event;
        Log.i(TAG, "onEventMainThread: 111111111111111" + event.getList());
        ThreadUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                mResideMenu.closeMenu();
                mRvComputer.setLayoutManager(new LinearLayoutManager(ComputerActivity.this));
                mRvComputer.setAdapter(new ComputerListAdapter(event.getList()));
//                Toast.makeText(ComputerActivity.this, event1.getList().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    protected void init() {
        super.init();


        mBarTitle.setText("上机查询");

        Log.i(TAG, "init: 出");
        EventBus.getDefault().register(this);

        mComputerPresent = new ComputerPresentImpl(this);
        mComputerPresent.getindex();
        initResideMenu();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initResideMenu() {
        mResideMenu = new ResideMenu(this);
        mResideMenu.setBackground(R.color.colorPrimary);
        mResideMenu.attachToActivity(this);

        mItem1 = new ResideMenuItem(this, mIcon[0], mTitles[0]);
        mItem2 = new ResideMenuItem(this, mIcon[1], mTitles[1]);
        mItem1.setOnClickListener(this);
        mItem2.setOnClickListener(this);
        mResideMenu.addMenuItem(mItem1, ResideMenu.DIRECTION_LEFT);
        mResideMenu.addMenuItem(mItem2, ResideMenu.DIRECTION_LEFT);
        mResideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
    }

    @Override
    public void onClick(View view) {
        if (view == mItem1) {
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
            mComputerPresent.getWeekData1();
        } else if (view == mItem2) {
            Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
            mComputerPresent.getWeekData2();
        }
    }

    @Override
    public void openResideMenuofRigth(final List<HashMap<String, String>> list) {
        ThreadUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {

                mItem = new ResideMenuItem[list.size()];
                List<ResideMenuItem> menuItems = new ArrayList<ResideMenuItem>();
                for (int i = 0; i < list.size(); i++) {
                    mItem[i] = new ResideMenuItem(ComputerActivity.this);
                    mItem[i].setTitle(list.get(i).get("name"));
                    final int finalI = i;
                    mItem[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(ComputerActivity.this, "" + list.get(finalI).get("name"), Toast.LENGTH_SHORT).show();
                            mComputerPresent.onloadData1(list.get(finalI).get("href"));
                        }
                    });
                    menuItems.add(mItem[i]);

                }
                mResideMenu.setMenuItems(menuItems, ResideMenu.DIRECTION_RIGHT);
                mResideMenu.closeMenu();

                mResideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);


            }
        });


    }

}

