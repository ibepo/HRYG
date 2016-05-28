// (c)2016 Flipboard Inc, All Rights Reserved.

package com.hryg.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hryg.base.MyTextUtils;
import com.hryg.model.GoodComment;
import com.kefanbufan.fengtimo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GoodsDetailCommentAdapter extends RecyclerView.Adapter {

    List<GoodComment.DataBean> list;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;

        if (MyTextUtils.isEmpty(list.get(position).getBuyer_name()))

        {
            debounceViewHolder.tvBuyerName.setText("");
        } else {
            debounceViewHolder.tvBuyerName.setText(list.get(position).getBuyer_name());

        }

        if (MyTextUtils.isEmpty(list.get(position).getComment()))

        {
            debounceViewHolder.tvComment.setText("");
        } else {
            debounceViewHolder.tvComment.setText(list.get(position).getComment());

        }

        if (MyTextUtils.isEmpty(list.get(position).getEvaluation_time()))

        {
            debounceViewHolder.tvEvaluationTime.setText("");
        } else {
            debounceViewHolder.tvEvaluationTime.setText(MyTextUtils.timeStamp2Date(list.get(position).getEvaluation_time()));

        }


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setData(List<GoodComment.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_buyer_name)
        TextView tvBuyerName;
        @Bind(R.id.tv_comment)
        TextView tvComment;
        @Bind(R.id.tv_evaluation_time)
        TextView tvEvaluationTime;

        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
