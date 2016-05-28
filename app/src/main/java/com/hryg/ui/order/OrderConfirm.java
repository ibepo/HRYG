package com.hryg.ui.order;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.OrderConfirmData;
import com.hryg.model.PostOrderData;
import com.hryg.network.Network;
import com.jakewharton.rxbinding.widget.RxRadioGroup;
import com.kefanbufan.fengtimo.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class OrderConfirm extends BaseActivity {


    @Bind(R.id.iv01)
    ImageView iv01;
    @Bind(R.id.iv02)
    ImageView iv02;
    @Bind(R.id.rb01)
    RadioButton rb01;
    @Bind(R.id.tvExpressName)
    TextView tvExpressName;
    @Bind(R.id.tvExpressMoney)
    TextView tvExpressMoney;
    @Bind(R.id.rb02)
    RadioButton rb02;
    @Bind(R.id.tvGold)
    TextView tvGold;
    @Bind(R.id.tvYunfei)
    TextView tvYunfei;
    @Bind(R.id.tvXiadan)
    TextView tvXiadan;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvPhone)
    TextView tvPhone;
    @Bind(R.id.tvAddress)
    TextView tvAddress;
    @Bind(R.id.tvSubtotal)
    TextView tvSubtotal;
    @Bind(R.id.tvHeji)
    TextView tvHeji;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;
    @Bind(R.id.rlOrderList)
    RelativeLayout rlOrderList;


    int gold, yunfei;
    OrderConfirmData orderConfirmData = new OrderConfirmData();
    String logist_fees;
    @Bind(R.id.rlAddress)
    RelativeLayout rlAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_confirm);
        ButterKnife.bind(this);
        getTopBar("确认订单");
        getData();
        initView();

    }

    public void initView() {
        rb01.setChecked(true);
        RxRadioGroup.checkedChanges(radioGroup).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {

                if (integer == R.id.rb01) {
                    tvYunfei.setText(tvExpressMoney.getText().toString());
                    int i = gold + yunfei;
                    tvHeji.setText("合计: ¥ " + i);
//                    logist_fees = orderConfirmData.getShipping().get(0).getType() + ";" + orderConfirmData.getShipping().get(0).getLogist_fees();
                } else {
                    tvYunfei.setText(" ¥ 0.00");
                    tvHeji.setText("合计: ¥ " + gold);
                }
            }
        });

    }


    public void postOrder() {

        showDialog();

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("address_options", orderConfirmData.getAddress().get(0).getAddr_id());
        map.put("consignee", orderConfirmData.getAddress().get(0).getConsignee());
        map.put("region_id", orderConfirmData.getAddress().get(0).getRegion_id());
        map.put("region_name", orderConfirmData.getAddress().get(0).getRegion_name());
        map.put("address", orderConfirmData.getAddress().get(0).getAddress());
        map.put("phone_tel", orderConfirmData.getAddress().get(0).getPhone_tel());
        map.put("logist_fees", logist_fees);


//        MediaType textType = MediaType.parse("text/plain");
//        RequestBody user_id = RequestBody.create(textType, PathConfig.user_id);
//        RequestBody address_options = RequestBody.create(textType,orderConfirmData.getAddress().get(0).getAddr_id());
//        RequestBody consignee = RequestBody.create(textType, orderConfirmData.getAddress().get(0).getConsignee());
//        RequestBody region_id = RequestBody.create(textType,orderConfirmData.getAddress().get(0).getRegion_id());
//        RequestBody region_name = RequestBody.create(textType, orderConfirmData.getAddress().get(0).getRegion_name());
//        RequestBody address = RequestBody.create(textType, orderConfirmData.getAddress().get(0).getAddress());
//        RequestBody phone_tel = RequestBody.create(textType,  orderConfirmData.getAddress().get(0).getPhone_tel());
//        RequestBody logist_fees = RequestBody.create(textType, "express:250");
//
        Map<String, Object> map2 = new HashMap<>();

        map2.put("user_id", "20446");
        map2.put("address_options", "3645");
        map2.put("consignee", "王");
        map2.put("region_id", "15034");
        map2.put("region_name", "黑龙江省哈尔滨市道里区");
        map2.put("address", "2222");
        map2.put("phone_tel", "15004043523");
        map2.put("logist_fees", "express:29");


        Network.getOrderApi().postOrder(map2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);
    }


    private Observer<PostOrderData> observer2 = new Observer<PostOrderData>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(PostOrderData data) {
            dimissDialog();
            if (data.getCode() == 1) {
                Intent intent2 = new Intent(OrderConfirm.this, PayType.class);
                OrderConfirm.this.startActivity(intent2);
            } else {
                ToastUtils.showSuperToastAlert(OrderConfirm.this, data.getDescription());
            }

        }

    };


    public void getData() {
        showDialog();
        Network.getOrderApi().getOrderConfirmData(PathConfig.user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<OrderConfirmData> observer = new Observer<OrderConfirmData>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(OrderConfirmData data) {
            dimissDialog();
            orderConfirmData = data;

            tvName.setText(data.getAddress().get(0).getConsignee());
            tvPhone.setText(data.getAddress().get(0).getPhone_tel());
            tvAddress.setText(data.getAddress().get(0).getAddress());
            tvSubtotal.setText("共" + data.getData().size() + "件");

            tvExpressName.setText(data.getShipping().get(0).getName());
            tvExpressMoney.setText("¥ " + data.getShipping().get(0).getLogist_fees() + "");

            tvYunfei.setText("¥ " + data.getShipping().get(0).getLogist_fees() + "");
            yunfei = data.getShipping().get(0).getLogist_fees();

            tvGold.setText("¥ " + data.getAmount_gold() + "");
            gold = data.getAmount_gold();

            int i = (int) data.getShipping().get(0).getLogist_fees() + (int) data.getAmount_gold();
            tvHeji.setText("合计: ¥ " + i);

            Glide.with(OrderConfirm.this).load(data.getData().get(0).getGoods_image()).into(iv01);
            if (data.getData().size() >= 2) {
                Glide.with(OrderConfirm.this).load(data.getData().get(1).getGoods_image()).into(iv02);
            }

            logist_fees = orderConfirmData.getShipping().get(0).getType() + ":" + orderConfirmData.getShipping().get(0).getLogist_fees();
        }

    };


    @OnClick({R.id.rb01, R.id.rb02, R.id.tvXiadan, R.id.rlOrderList, R.id.rlAddress})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tvXiadan:
                postOrder();
                break;
            case R.id.rlAddress:
                Intent intent2 = new Intent(this, Address.class);
                this.startActivity(intent2);
                break;
            case R.id.rlOrderList:
                Intent intent = new Intent(this, OrderList.class);
                this.startActivity(intent);
                break;
        }
    }


}
