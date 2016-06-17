package com.hryg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hryg.model.PeiIdListBean;
import com.hryg.ui.mine.IDGroupList;
import com.kefanbufan.fengtimo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GroupAdapter extends RecyclerView.Adapter {

    List<PeiIdListBean.DataBean> list;
    static Context context;
    List<Integer> checkdedList = new ArrayList<>();
    List<String> idList = new ArrayList<>();


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final DebounceViewHolder viewHolder = (DebounceViewHolder) holder;
        viewHolder.tvName.setText(list.get(position).getName());


        if (checkdedList != null) {
            viewHolder.cbID.setChecked(checkdedList.contains(new Integer(position)) ? true : false);
        } else {
            viewHolder.cbID.setChecked(false);
        }


        viewHolder.cbID.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {


                if (isChecked) {
                    if (!checkdedList.contains(viewHolder.cbID.getTag())) {
                        checkdedList.add(new Integer(position));
                        idList.remove(new Integer(position));
                    }
                } else {
                    if (checkdedList.contains(viewHolder.cbID.getTag())) {
                        checkdedList.remove(new Integer(position));
                        idList.add(new Integer(position), list.get(position).getId());

                    }
                }

                IDGroupList.IDList = idList;

            }
        });


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setImages(List<PeiIdListBean.DataBean> list, Context context) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.cbID)
        CheckBox cbID;

        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
