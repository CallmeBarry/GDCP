package com.qqdemo.administrator.gdcp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qqdemo.administrator.gdcp.R;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/12/30.
 */

public class DateListAdapter extends RecyclerView.Adapter<DateListAdapter.DateListItemViewHolder> {
    private List<Map<String, Object>> dateslist;

    public DateListAdapter(List<Map<String, Object>>  list) {
        dateslist = list;
    }

    @Override
    public DateListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datelist_item, parent, false);
        DateListItemViewHolder holder = new DateListItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DateListAdapter.DateListItemViewHolder holder, int position) {

        Map<String, Object> point = dateslist.get(position);
        String title = (String) point.get("title");
        String time = (String) point.get("time");
        holder.txt.setText(title);
        holder.time.setText(time);


    }

    @Override
    public int getItemCount() {
        return dateslist.size();
    }

    public class DateListItemViewHolder extends RecyclerView.ViewHolder {

        private TextView txt;
        private TextView time;

        public DateListItemViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.tv_txt);
            time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
