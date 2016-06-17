// (c)2016 Flipboard Inc, All Rights Reserved.

package com.hryg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hryg.model.AddressListData;
import com.kefanbufan.fengtimo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageAdapter extends RecyclerView.Adapter {

    List<AddressListData.DataBean> list;
    static Context context;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DebounceViewHolder viewHolder = (DebounceViewHolder) holder;
//        Glide.with(holder.itemView.getContext()).load(list.get(position).getGoods_image()).into(viewHolder.imageIv);

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

        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
