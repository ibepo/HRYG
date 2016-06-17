// (c)2016 Flipboard Inc, All Rights Reserved.

package com.hryg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.ResultBean4Rep;
import com.hryg.model.ReviewListBean;
import com.hryg.network.Network;
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

public class ReviewListAdapter extends RecyclerView.Adapter {

    List<ReviewListBean.DataBean> list;
    static Context context;
    SweetAlertDialog ssdialog;
    int positionFlag;


    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
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
                new SweetAlertDialog(view.getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("是否批准成为商家?")
                        .showContentText(false)
                        .setCancelText("拒绝")
                        .setConfirmText("通过")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {

                                review(list.get(position).getUserId());
                                positionFlag = position;
                                sDialog.cancel();
                                ssdialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
                                        .setTitleText("正在操作...");

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

    public void setImages(List<ReviewListBean.DataBean> list, Context context) {
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


    Observer<ResultBean4Rep> observer2 = new Observer<ResultBean4Rep>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            ToastUtils.showSuperToastAlertGreen(context, "连接服务器失败");
        }

        @Override
        public void onNext(ResultBean4Rep resultBean) {
            ssdialog.cancel();
            ToastUtils.showSuperToastAlertGreen(context, resultBean.getDescription());
            list.remove(positionFlag);
            notifyItemRemoved(positionFlag);
            if (resultBean.getCode() == 1)
                notifyDataSetChanged();
        }

    };

    void review(String userid) {

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("userId", userid);
        Network.getMineApi().review(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);


    }


}
