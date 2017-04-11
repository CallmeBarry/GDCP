package com.qqdemo.administrator.gdcp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qqdemo.administrator.gdcp.R;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 2016/12/30.
 */

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListAdapter.ScoreListItemViewHolder> {
    String TAG="11111111111111111111111111";
    private List<HashMap<String, Object>> mScoreList;

    public ScoreListAdapter(List<HashMap<String, Object>> scoreList) {
        mScoreList = scoreList;
    }

    @Override
    public ScoreListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_item, parent, false);
        ScoreListItemViewHolder holder = new ScoreListItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ScoreListAdapter.ScoreListItemViewHolder holder, int position) {

                HashMap<String, Object> scroe = mScoreList.get(position);
                holder.iname.setText(scroe.get("iname").toString());
                holder.iscroe.setText(scroe.get("iscore").toString());

    }

    @Override
    public int getItemCount() {
        return mScoreList.size();
    }

    public class ScoreListItemViewHolder extends RecyclerView.ViewHolder {

        TextView iname;
        TextView iscroe;

        public ScoreListItemViewHolder(View itemView) {
            super(itemView);
            iname = (TextView) itemView.findViewById(R.id.tv_iname);
            iscroe = (TextView) itemView.findViewById(R.id.tv_iscore);
        }
    }
}
