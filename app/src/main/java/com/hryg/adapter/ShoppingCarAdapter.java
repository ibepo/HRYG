// (c)2016 Flipboard Inc, All Rights Reserved.

package com.hryg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.ResultBean;
import com.hryg.model.ShoppingCar;
import com.hryg.network.Network;
import com.hryg.ui.order.OrderConfirm;
import com.kefanbufan.fengtimo.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShoppingCarAdapter extends RecyclerView.Adapter {

    List<ShoppingCar.DataBean> list;
    static Context context;
    SweetAlertDialog ssdialog;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_car_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        Glide.with(holder.itemView.getContext()).load(list.get(position).getGoods_image()).into(debounceViewHolder.imageIv);
        debounceViewHolder.descriptionTv.setText(list.get(position).getGoods_name());
        debounceViewHolder.gold.setText(list.get(position).getGold());
        debounceViewHolder.tvQuantity.setText(list.get(position).getQuantity());
        debounceViewHolder.tvSubtotal.setText("总价: " + list.get(position).getSubtotal());


        debounceViewHolder.tvJieSuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderConfirm.class);
                context.startActivity(intent);


            }
        });


        debounceViewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(view.getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("您确定要删除此商品!")
                        .showContentText(false)
                        .setConfirmText("是的")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                dropGoods(list.get(position).getRec_id());
                                list.remove(position);
                                notifyItemRemoved(position);
                                sDialog.cancel();
                                ssdialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
                                        .setTitleText("正在删除...");

                                ssdialog.show();

                            }
                        })
                        .setCancelText("取消")
                        .setCancelClickListener(null)
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setImages(List<ShoppingCar.DataBean> list, Context context) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageIv)
        ImageView imageIv;
        @Bind(R.id.ivDel)
        ImageView ivDel;
        @Bind(R.id.descriptionTv)
        TextView descriptionTv;
        @Bind(R.id.gold)
        TextView gold;

        @Bind(R.id.tvQuantity)
        TextView tvQuantity;
        @Bind(R.id.tvSubtotal)
        TextView tvSubtotal;

        @Bind(R.id.tvJieSuan)
        TextView tvJieSuan;

        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


    public void dropGoods(String rec_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("rec_id", rec_id);

        Network.getGoodsApi().dropGoods(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    Observer<ResultBean> observer = new Observer<ResultBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {

            ToastUtils.showSuperToastAlertGreen(context, "连接服务器失败");
        }

        @Override
        public void onNext(ResultBean resultBean) {
            ssdialog.cancel();
            ToastUtils.showSuperToastAlertGreen(context, resultBean.getDescription());
            if (resultBean.getCode() == 1)
                notifyDataSetChanged();
        }

    };


}
