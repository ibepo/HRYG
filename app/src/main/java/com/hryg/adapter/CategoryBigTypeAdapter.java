
package com.hryg.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hryg.model.Category;
import com.hryg.ui.home.CategoryFragment;
import com.kefanbufan.fengtimo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryBigTypeAdapter extends RecyclerView.Adapter {

    static List<Category.DataBean> list;
    Category category = new Category();
    List<Integer> checkdedList = new ArrayList<>();
    Context context;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_big_type_item, parent, false);
        return new DebounceViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        debounceViewHolder.tvBigType.setText(list.get(position).getName());
        debounceViewHolder.rlBigType.setTag(position);


        debounceViewHolder.rlBigType.setBackgroundColor(Color.parseColor("#FFFFFF"));
        debounceViewHolder.tvBigType.setTextColor(Color.parseColor("#000000"));

        if (checkdedList != null) {
            if (checkdedList.contains(position)) {
                debounceViewHolder.rlBigType.setBackgroundColor(Color.parseColor("#f0f0f0"));
                debounceViewHolder.tvBigType.setTextColor(Color.parseColor("#FC4B4E"));
            }
        }

        debounceViewHolder.rlBigType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkdedList.clear();
                checkdedList.add(position);
                notifyDataSetChanged();
                CategoryFragment.categorySmallTypeAdapter.setImages(category.getData().get(position).getChildren(), context);
            }
        });


    }

    @Override
    public int getItemCount() {

        if (list == null) {
            return 0;
        } else {
            return list.size();
        }

    }


    public void setAllData(Category category) {
        this.category = category;

    }

    public void setData(List<Category.DataBean> list, Context context) {
        this.context = context;
        this.list = list;
        checkdedList.add(0);
        notifyDataSetChanged();
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        debounceViewHolder.rlBigType.setTag(-2);
        super.onViewRecycled(holder);
    }

    public class DebounceViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvBigType)
        TextView tvBigType;
        @Bind(R.id.rlBigType)
        RelativeLayout rlBigType;

        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }
}



