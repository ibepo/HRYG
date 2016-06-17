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

import com.bumptech.glide.Glide;
import com.hryg.model.BuyOrderListData;
import com.hryg.ui.buyorder.SubCommentGoodsList;
import com.hryg.ui.buyorder.SubmitComment2;
import com.kefanbufan.fengtimo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CommetGoodsListAdapter extends RecyclerView.Adapter {

    List<BuyOrderListData.DataBean> list;
    static Context context;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_goods_list_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        Glide.with(holder.itemView.getContext()).load(list.get(position).getGoods_image()).into(debounceViewHolder.imageIv);
        debounceViewHolder.descriptionTv.setText(list.get(position).getGoods_name());
        debounceViewHolder.gold.setText("¥" + list.get(position).getGold());
        debounceViewHolder.tvStore.setText(list.get(position).getStore_name());

//        ImageAdapter imageAdapter = new ImageAdapter();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        debounceViewHolder.recView.setLayoutManager(linearLayoutManager);
//        debounceViewHolder.recView.setAdapter(imageAdapter);


        debounceViewHolder.tvType.setText("待评价");
        debounceViewHolder.ivDel.setVisibility(View.VISIBLE);
        debounceViewHolder.tvAction.setText("评价");
        debounceViewHolder.tvAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(context, SubmitComment2.class);
                intent2.putExtra("index", position + "");
                intent2.putExtra("order_id", SubCommentGoodsList.order_id);
                context.startActivity(intent2);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setImages(List<BuyOrderListData.DataBean> list, Context context) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageIv)
        ImageView imageIv;
        @Bind(R.id.descriptionTv)
        TextView descriptionTv;
        @Bind(R.id.gold)
        TextView gold;
        @Bind(R.id.ivDel)
        ImageView ivDel;
        @Bind(R.id.tvType)
        TextView tvType;
        @Bind(R.id.tvAction)
        TextView tvAction;
        @Bind(R.id.tvStore)
        TextView tvStore;
        @Bind(R.id.recView)
        RecyclerView recView;
        @Bind(R.id.linMain)
        LinearLayout linMain;

        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
