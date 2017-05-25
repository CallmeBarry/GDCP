/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qqdemo.administrator.gdcp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.view.OnItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;
import java.util.Map;

public class DragTouchAdapter extends SwipeMenuAdapter<DragTouchAdapter.DefaultViewHolder> {

    private SwipeMenuRecyclerView mMenuRecyclerView;

//    private List<String> titles;
    private static List<Map<String, Object>> mList;

    private OnItemClickListener mOnItemClickListener;

    public DragTouchAdapter(SwipeMenuRecyclerView menuRecyclerView,List<Map<String, Object>> titles) {
        this.mMenuRecyclerView = menuRecyclerView;
        this.mList = titles;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drag_touch, parent, false);
    }

    @Override
    public DragTouchAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        DefaultViewHolder viewHolder = new DefaultViewHolder(realContentView);
        viewHolder.mOnItemClickListener = mOnItemClickListener;
        viewHolder.mMenuRecyclerView = mMenuRecyclerView;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DragTouchAdapter.DefaultViewHolder holder, int position) {
        holder.setData(mList.get(position));
    }


    static class DefaultViewHolder extends RecyclerView.ViewHolder implements  View.OnTouchListener {

        TextView tvTitle;
        SwitchButton switchButton;
        OnItemClickListener mOnItemClickListener;
        SwipeMenuRecyclerView mMenuRecyclerView;

        public DefaultViewHolder(View itemView) {
            super(itemView);
//            itemView.setOnClickListener(this);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            switchButton = (SwitchButton) itemView.findViewById(R.id.switchButton);
            itemView.findViewById(R.id.iv_touch).setOnTouchListener(this);
        }

        public void setData(Map<String, Object> map) {
            this.tvTitle.setText((String)map.get("tittle"));
            this.switchButton.setChecked((Boolean) map.get("key"));
            switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    boolean key = (boolean) mList.get(getAdapterPosition()).get("key");
                    mOnItemClickListener.onItemClick(getAdapterPosition(),!key);
                }
            });
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN: {
                    mMenuRecyclerView.startDrag(this);
                    break;
                }
            }
            return false;
        }
    }




}
