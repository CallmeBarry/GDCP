package com.qqdemo.administrator.gdcp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kelin.scrollablepanel.library.PanelAdapter;
import com.qqdemo.administrator.gdcp.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */

public  class ScheduleAdapter extends PanelAdapter {
    private  List<List<String>> data;

    public  ScheduleAdapter(List<List<String>> data) {
        this.data = data;
    }

    @Override
    public synchronized int getRowCount() {
        return data.size();
    }

    @Override
    public synchronized int getColumnCount() {
        return data.get(0).size();
    }

    @Override
    public synchronized int getItemViewType(int row, int column) {
        return super.getItemViewType(row, column);
    }

    @Override
    public synchronized void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        String title = data.get(row).get(column);
        TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
        titleViewHolder.titleTextView.setText(title);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScheduleAdapter.TitleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_title, parent, false));
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TitleViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
        }
    }
}
