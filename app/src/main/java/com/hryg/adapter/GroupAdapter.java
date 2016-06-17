// (c)2016 Flipboard Inc, All Rights Reserved.

package com.hryg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hryg.model.IdListBean;
import com.hryg.ui.mine.IDGroupList;
import com.kefanbufan.fengtimo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GroupAdapter extends RecyclerView.Adapter {

    List<IdListBean.DataBean> list;
    static Context context;


    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.downline_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DebounceViewHolder viewHolder = (DebounceViewHolder) holder;
        viewHolder.tvName.setText(list.get(position).getUser_name());

        if (list.get(position).getPhone_tel() == null) {
            viewHolder.tvPhone.setText("");
        } else {
            viewHolder.tvPhone.setText(list.get(position).getPhone_tel().toString());
        }

        viewHolder.tvAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent13 = new Intent(context, IDGroupList.class);
                intent13.putExtra("pei_id", list.get(position).getUserId());
                context.startActivity(intent13);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setImages(List<IdListBean.DataBean> list, Context context) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.tvPhone)
        TextView tvPhone;
        @Bind(R.id.tvAction)
        TextView tvAction;

        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
