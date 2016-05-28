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
import com.hryg.model.Category;
import com.hryg.ui.search.Search;
import com.kefanbufan.fengtimo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategorySmallTypeAdapter extends RecyclerView.Adapter {

    List<Category.DataBean.ChildrenBean> list;
    Context context;
    Category category = new Category();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_small_type_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        Glide.with(holder.itemView.getContext()).load(list.get(position).getImg()).into(debounceViewHolder.imageIv);
        debounceViewHolder.descriptionTv.setText(list.get(position).getName());
        debounceViewHolder.linItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Search.class);
                intent.putExtra("cate_id", list.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setImages(List<Category.DataBean.ChildrenBean> list, Context context) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }


    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageIv)
        ImageView imageIv;
        @Bind(R.id.descriptionTv)
        TextView descriptionTv;
        @Bind(R.id.linItem)
        LinearLayout linItem;


        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
