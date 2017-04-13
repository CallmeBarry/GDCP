package com.qqdemo.administrator.gdcp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
        final String title = data.get(row).get(column);
        TitleViewHolder titleViewHolder = (TitleViewHolder) holder;

        String[] str = title.split(" ");
        if(str.length>2){
            String zi;
            if (str[0].length()>12){
                zi=str[0].substring(0,12)+"...";
                titleViewHolder.titleTextView.setText(zi);
            }else{
                titleViewHolder.titleTextView.setText(str[0]);
            }
            if(row!=0 && column!=0){
                final StringBuffer buffer=new StringBuffer();
                for(String s:str){
                    buffer.append(s+"\t\n");
                }
                titleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(),buffer , Toast.LENGTH_SHORT).show();
                    }
                });}
        }else{
            titleViewHolder.titleTextView.setText(title);
        }

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
