package com.qqdemo.administrator.gdcp.ui.activity;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.adapter.DragTouchAdapter;
import com.qqdemo.administrator.gdcp.model.MyEvent;
import com.qqdemo.administrator.gdcp.presenter.SettingPresent;
import com.qqdemo.administrator.gdcp.presenter.impl.SettingPresentImpl;
import com.qqdemo.administrator.gdcp.view.OnItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemStateChangedListener;

import org.greenrobot.eventbus.EventBus;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    private static final String TAG = "SettingActivity";
    @BindView(R.id.bar_title)
    TextView mBarTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;
    @BindView(R.id.activity_setting)
    LinearLayout mActivitySetting;
    private DragTouchAdapter mDragAdapter;

    private SettingPresent mSettingPresent;


    private List<Map<String, Object>> mMaps;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {
        super.init();

        mBack.setVisibility(View.VISIBLE);
        mBarTitle.setText("栏目编辑");


        SwipeMenuRecyclerView menuRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recycler_view);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。

        mSettingPresent = new SettingPresentImpl(getApplicationContext());
        mMaps = mSettingPresent.loadTittle();


        // 触摸拖拽的代码在Adapter中：SwipeMenuRecyclerView#startDrag(ViewHolder);
        mDragAdapter = new DragTouchAdapter(menuRecyclerView, mMaps);
        mDragAdapter.setOnItemClickListener(onItemClickListener);
        menuRecyclerView.setAdapter(mDragAdapter);

        menuRecyclerView.setLongPressDragEnabled(true); // 开启拖拽。
        menuRecyclerView.setOnItemMoveListener(onItemMoveListener);// 监听拖拽，更新UI。
        menuRecyclerView.setOnItemStateChangedListener(mOnItemStateChangedListener);
    }

    /**
     * 当Item移动的时候。
     */
    private OnItemMoveListener onItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            Collections.swap(mMaps, fromPosition, toPosition);
            mDragAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(int position) {
        }

    };


    /**
     * Item的拖拽/侧滑删除时，手指状态发生变化监听。
     */
    private OnItemStateChangedListener mOnItemStateChangedListener = new OnItemStateChangedListener() {
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState == OnItemStateChangedListener.ACTION_STATE_DRAG) {
//            mActionBar.setSubtitle("状态：拖拽");

                // 拖拽的时候背景就透明了，这里我们可以添加一个特殊背景。
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(SettingActivity.this, R.color.white_pressed));
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_IDLE) {
//            mActionBar.setSubtitle("状态：手指松开");

                // 在手松开的时候还原背景。
                ViewCompat.setBackground(viewHolder.itemView, ContextCompat.getDrawable(SettingActivity.this, R.drawable.select_white));
            }
        }
    };

    @Override
    public void onBackPressed() {
        savetittles();
        super.onBackPressed();
    }


    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position, boolean isCheck) {
            mMaps.get(position).put("key", isCheck);
            Log.i(TAG, "onItemClick: " + mMaps);
        }
    };

    private void savetittles() {
        Map<String, Object> aa = new HashMap<>();
        MyEvent myEvent = new MyEvent();
        myEvent.setMaps(mMaps);
        EventBus.getDefault().post(myEvent);
        aa.put("tittles", mMaps);
        mSettingPresent.saveTittle(aa);
    }

    @OnClick(R.id.back)
    public void onClick() {
        savetittles();
        finish();
    }
}