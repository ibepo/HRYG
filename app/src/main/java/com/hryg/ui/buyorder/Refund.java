package com.hryg.ui.buyorder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.OrderDetailBean;
import com.hryg.network.Network;
import com.kefanbufan.fengtimo.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Refund extends BaseActivity {


    String user_id, order_id;
    @Bind(R.id.tvNum)
    TextView tvNum;
    @Bind(R.id.tvStatus)
    TextView tvStatus;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.linGoods)
    LinearLayout linGoods;
    @Bind(R.id.tvType)
    TextView tvType;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvMyPhone)
    TextView tvMyPhone;
    @Bind(R.id.tvAddress)
    TextView tvAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_order_detail);
        ButterKnife.bind(this);
        getTopBar("订单详情");

        user_id = PathConfig.user_id;
        order_id = getIntent().getExtras().get("order_id").toString();
        getData();
    }


    public void getData() {
        showDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("order_id", order_id);

        Network.getOrderApi().getOrderDetail(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }


    Observer<OrderDetailBean> observer = new Observer<OrderDetailBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            dimissDialog();
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(OrderDetailBean data) {
            dimissDialog();

            // 一楼
            tvNum.setText("订单编号: " + data.getData().getOrder_sn());
            tvTime.setText("下单时间: " + data.getData().getAdd_time());
            switch (Integer.parseInt(data.getData().getStatus())) {
                case 0:
                    tvStatus.setText("已取消");
                    break;
                case 11:
                    tvStatus.setText("未付款");
                    break;
                case 20:
                    tvStatus.setText("代发货");
                    break;
                case 30:
                    tvStatus.setText("待收货");
                    break;
                case 40:
                    tvStatus.setText("已完成");
                    break;
                case 80:
                    tvStatus.setText("已退款");
                    break;
            }


            //二楼


            for (int j = 0; j < data.getGoods().size(); j++) {
                LayoutInflater inflate = LayoutInflater.from(getApplicationContext());
                View view = inflate.inflate(R.layout.orderdetail_good_item, null);
                TextView tvStore = (TextView) view.findViewById(R.id.tvStore);
                TextView tvPhone = (TextView) view.findViewById(R.id.tvPhone);
                TextView descriptionTv = (TextView) view.findViewById(R.id.descriptionTv);
                TextView tvTotal = (TextView) view.findViewById(R.id.tvTotal);
                TextView tvGold = (TextView) view.findViewById(R.id.tvGold);
                ImageView imageIv = (ImageView) view.findViewById(R.id.imageIv);

                tvStore.setVisibility(View.GONE);
                descriptionTv.setText(data.getGoods().get(j).getGoods_name());
                tvTotal.setText("数量: " + data.getGoods().get(j).getQuantity());
                tvGold.setText("¥" + data.getGoods().get(j).getFu_gold());
                Glide.with(getApplicationContext()).load(data.getGoods().get(j).getGoods_image()).into(imageIv);

                linGoods.addView(view);
            }


            //三楼
            tvType.setText(data.getData().getPayment_name());


            //四楼
            tvName.setText(data.getExtm().getConsignee());
            tvMyPhone.setText(data.getExtm().getPhone_tel());
            tvAddress.setText(data.getExtm().getRegion_name());


        }

    };


}
