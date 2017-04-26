package com.qqdemo.administrator.gdcp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qqdemo.administrator.gdcp.R;

import java.util.List;


/**
 * Created by Administrator on 2016/12/30.
 */

public class ComputerListAdapter extends RecyclerView.Adapter<ComputerListAdapter.ComputerListItemViewHolder> {
    private List<String> mDataList;

    public ComputerListAdapter(List<String> list) {
        mDataList = list;
    }

    @Override
    public ComputerListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.computerlist_item, parent, false);
        ComputerListItemViewHolder holder = new ComputerListItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ComputerListAdapter.ComputerListItemViewHolder holder, int position) {

        String data = mDataList.get(position);
        holder.txt.setText(data);


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ComputerListItemViewHolder extends RecyclerView.ViewHolder {

        TextView txt;

        public ComputerListItemViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.tv_txt);
        }
    }
}
