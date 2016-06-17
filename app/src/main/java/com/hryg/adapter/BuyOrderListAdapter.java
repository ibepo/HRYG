// (c)2016 Flipboard Inc, All Rights Reserved.

package com.hryg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.BuyOrderListData;
import com.hryg.model.ResultBean;
import com.hryg.model.ResultBean4Rep;
import com.hryg.network.Network;
import com.hryg.ui.buyorder.OrderDetail;
import com.hryg.ui.buyorder.Refund;
import com.hryg.ui.buyorder.SubmitComment;
import com.hryg.ui.order.PayType;
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

public class BuyOrderListAdapter extends RecyclerView.Adapter {

    List<BuyOrderListData.DataBean> list;
    static Context context;
    SweetAlertDialog ssdialog;
    int positionFlag;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_orderlist_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        Glide.with(holder.itemView.getContext()).load(list.get(position).getGoods_image()).into(debounceViewHolder.imageIv);
        debounceViewHolder.descriptionTv.setText(list.get(position).getGoods_name());
        debounceViewHolder.gold.setText("¥" + list.get(position).getGold());
        debounceViewHolder.tvStore.setText(list.get(position).getStore_name());

        debounceViewHolder.linMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(context, OrderDetail.class);
                intent2.putExtra("order_id", list.get(position).getOrder_id());
                intent2.putExtra("store_id", list.get(position).getStore_id());
                context.startActivity(intent2);
            }
        });


        ImageAdapter imageAdapter = new ImageAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        debounceViewHolder.recView.setLayoutManager(linearLayoutManager);
        debounceViewHolder.recView.setAdapter(imageAdapter);
//        imageAdapter.setImages(list.get(position).getGoods_image());
        debounceViewHolder.tvAction2.setVisibility(View.GONE);
        debounceViewHolder.tvAction3.setVisibility(View.GONE);


        switch (Integer.parseInt(list.get(position).getStatus())) {

            case 0:
                debounceViewHolder.tvType.setText("已取消");
                debounceViewHolder.tvAction.setText("查看订单");
                debounceViewHolder.tvAction.setVisibility(View.VISIBLE);
                debounceViewHolder.tvAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent2 = new Intent(context, OrderDetail.class);
                        intent2.putExtra("order_id", list.get(position).getOrder_id());
                        intent2.putExtra("store_id", list.get(position).getStore_id());
                        context.startActivity(intent2);

                    }
                });
                debounceViewHolder.ivDel.setVisibility(View.VISIBLE);
                debounceViewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new SweetAlertDialog(view.getContext(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("您确定要删除此商品?")
                                .showContentText(false)
                                .setConfirmText("是的")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        dropGoods(list.get(position).getOrder_id());
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


                break;
            case 11:
                debounceViewHolder.tvType.setText("待付款");
                debounceViewHolder.ivDel.setVisibility(View.GONE);
                debounceViewHolder.tvAction2.setVisibility(View.VISIBLE);
                debounceViewHolder.tvAction2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        new SweetAlertDialog(view.getContext(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("您确定要取消订单吗?")
                                .showContentText(false)
                                .setConfirmText("是的")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        cancelOrder(list.get(position).getOrder_id());
                                        list.remove(position);
                                        notifyItemRemoved(position);
                                        sDialog.cancel();
                                        ssdialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
                                                .setTitleText("正在取消...");

                                        ssdialog.show();

                                    }
                                })
                                .setCancelText("取消")
                                .setCancelClickListener(null)
                                .show();


                    }
                });

                debounceViewHolder.tvAction.setText("去支付");
                debounceViewHolder.tvAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent2 = new Intent(context, PayType.class);
                        intent2.putExtra("order_id", list.get(position).getOrder_id());
                        intent2.putExtra("store_id", list.get(position).getStore_id());
                        context.startActivity(intent2);
                    }
                });

                break;
            case 20:
                debounceViewHolder.ivDel.setVisibility(View.GONE);
                switch (Integer.parseInt(list.get(position).getApply_status())) {

                    case 1:
                        debounceViewHolder.tvType.setText("待退款");
                        debounceViewHolder.tvAction.setText("查看订单");
                        debounceViewHolder.tvAction.setVisibility(View.VISIBLE);
                        debounceViewHolder.tvAction.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent2 = new Intent(context, OrderDetail.class);
                                intent2.putExtra("order_id", list.get(position).getOrder_id());
                                intent2.putExtra("store_id", list.get(position).getStore_id());
                                context.startActivity(intent2);

                            }
                        });
                        break;
                    default:
                        debounceViewHolder.tvType.setText("待发货");
                        debounceViewHolder.tvAction.setVisibility(View.GONE);
                        debounceViewHolder.tvAction3.setVisibility(View.VISIBLE);
                        debounceViewHolder.tvAction3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent2 = new Intent(context, Refund.class);
                                intent2.putExtra("order_id", list.get(position).getOrder_id());
                                intent2.putExtra("store_id", list.get(position).getStore_id());
                                context.startActivity(intent2);
                            }
                        });
                        break;
                }


                break;
            case 30:
                debounceViewHolder.ivDel.setVisibility(View.GONE);
                switch (Integer.parseInt(list.get(position).getApply_status())) {

                    case 1:
                        debounceViewHolder.tvType.setText("待收货");
                        debounceViewHolder.tvAction.setText("确认收货");
                        debounceViewHolder.tvAction.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                new SweetAlertDialog(view.getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("确定收货?")
                                        .showContentText(false)
                                        .setConfirmText("是的")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                confirmOrder(list.get(position).getOrder_id());
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
                        break;
                    default:
                        debounceViewHolder.tvType.setText("待退款");
                        debounceViewHolder.tvAction.setVisibility(View.GONE);
                        debounceViewHolder.tvAction3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent2 = new Intent(context, Refund.class);
                                intent2.putExtra("order_id", list.get(position).getOrder_id());
                                intent2.putExtra("store_id", list.get(position).getStore_id());
                                context.startActivity(intent2);
                            }
                        });
                        break;
                }


                break;
            case 40:
                debounceViewHolder.tvType.setText("待评价");
                debounceViewHolder.ivDel.setVisibility(View.VISIBLE);
                switch (Integer.parseInt(list.get(position).getEvaluation_status())) {
                    case 0:
                        debounceViewHolder.tvAction.setText("评价");
                        debounceViewHolder.tvAction.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent2 = new Intent(context, SubmitComment.class);
                                intent2.putExtra("order_id", list.get(position).getOrder_id());
                                context.startActivity(intent2);
                            }
                        });
                        break;
                    case 1:
                        debounceViewHolder.tvAction.setText("已评价");

                }

                break;
            case 80:


                switch (Integer.parseInt(list.get(position).getApply_status())) {
                    case 0:

                        break;
                }
                debounceViewHolder.ivDel.setVisibility(View.GONE);
                debounceViewHolder.tvType.setText("已退款");
                debounceViewHolder.tvAction.setText("查看订单");
                debounceViewHolder.tvAction.setVisibility(View.VISIBLE);
                debounceViewHolder.tvAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent2 = new Intent(context, OrderDetail.class);
                        intent2.putExtra("order_id", list.get(position).getOrder_id());
                        intent2.putExtra("store_id", list.get(position).getStore_id());
                        context.startActivity(intent2);

                    }
                });
                break;

        }


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


    public void addData(List<BuyOrderListData.DataBean> list) {
        int startPosition = getItemCount();
        this.list.addAll(list);
        notifyItemRangeInserted(startPosition, list.size());

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
        @Bind(R.id.tvAction2)
        TextView tvAction2;
        @Bind(R.id.tvAction3)
        TextView tvAction3;
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

    //收货
    public void confirmOrder(String order_id) {

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("order_id", order_id);

        Network.getOrderApi().confirmOrder(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);

    }


    //取消订单
    public void cancelOrder(String order_id) {

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("order_id", order_id);

        Network.getOrderApi().cancelOrder(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);

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
//            list.remove(positionFlag);
//            notifyItemRemoved(positionFlag);
            if (resultBean.getCode() == 1)
                notifyDataSetChanged();
        }

    };


    //删除一个订单
    public void dropGoods(String order_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("order_id", order_id);

        Network.getOrderApi().dropOrder(map)
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
