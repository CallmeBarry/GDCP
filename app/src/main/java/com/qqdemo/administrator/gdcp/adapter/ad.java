package com.qqdemo.administrator.gdcp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qqdemo.administrator.gdcp.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class ad extends RecyclerView.Adapter<ad.ViewHolder> {
    List list;

    ad(List list) {
        this.list = list;
    }

    @Override
    public ad.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.computerlist_item, parent, false);
        ViewHolder aa = new ViewHolder(view);
        return aa;
    }

    @Override
    public void onBindViewHolder(ad.ViewHolder holder, int position) {
        holder.txt.setText((Integer) list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;

        public ViewHolder(View itemView) {
            super(itemView);

            txt= (TextView) itemView.findViewById(R.id.tv_txt);
        }
    }
}
