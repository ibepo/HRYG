// (c)2016 Flipboard Inc, All Rights Reserved.

package com.hryg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hryg.model.AddressListData;
import com.hryg.ui.address.AddressModify;
import com.kefanbufan.fengtimo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReviewListAdapter extends RecyclerView.Adapter {

    List<AddressListData.DataBean> list;
    static Context context;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DebounceViewHolder viewHolder = (DebounceViewHolder) holder;
        viewHolder.tvName.setText(list.get(position).getConsignee());
        viewHolder.tvPhone.setText(list.get(position).getPhone_tel());
        viewHolder.tvAddress.setText(list.get(position).getRegion_name() + list.get(position).getAddress());

        viewHolder.linMofify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(context, AddressModify.class);
                intent2.putExtra("addr_id", list.get(position).getAddr_id());
                context.startActivity(intent2);
            }
        });

        if (Integer.parseInt(list.get(position).getIf_show()) == 1) {
            viewHolder.imageIv.setVisibility(View.VISIBLE);
        } else {
            viewHolder.imageIv.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setImages(List<AddressListData.DataBean> list, Context context) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageIv)
        ImageView imageIv;
        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.tvPhone)
        TextView tvPhone;
        @Bind(R.id.tvAddress)
        TextView tvAddress;
        @Bind(R.id.linMofify)
        LinearLayout linMofify;


        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
