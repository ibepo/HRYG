package com.hryg.ui.buyorder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.RefundBean;
import com.hryg.model.ResultBean4Rep;
import com.hryg.network.Network;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.kefanbufan.fengtimo.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Refund extends BaseActivity {


    String user_id, order_id;
    String amount, cause, instructions;

    @Bind(R.id.tvNum)
    TextView tvNum;
    @Bind(R.id.tvStatus)
    TextView tvStatus;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.linGoods)
    LinearLayout linGoods;
    @Bind(R.id.spinner)
    Spinner spinner;

    @Bind(R.id.tvAmount)
    TextView tvAmount;
    @Bind(R.id.etComment)
    EditText etComment;
    @Bind(R.id.tvAction)
    TextView tvAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refund);
        ButterKnife.bind(this);
        getTopBar("申请退款");

        user_id = PathConfig.user_id;
        order_id = getIntent().getExtras().get("order_id").toString();
        initView();
        getData();
    }


    void initView() {
        spinner.setDropDownVerticalOffset(-40);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                i = i + 1;
                cause = i + "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        RxTextView.textChanges(etComment).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                instructions = charSequence.toString();

            }
        });

        RxView.clicks(tvAction).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                submitRefund();
            }
        });

    }


    public void submitRefund() {


        showDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("order_id", order_id);
        map.put("amount", amount);
        map.put("cause", cause);
        map.put("instructions", instructions);

        Network.getOrderApi().refund(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);
    }


    Observer<ResultBean4Rep> observer2 = new Observer<ResultBean4Rep>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            dimissDialog();
            ToastUtils.showSuperToastAlert(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(ResultBean4Rep data) {
            dimissDialog();
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), data.getDescription());
            if (data.getCode() == 1) {
                finish();
            }
        }

    };


    public void getData() {
        showDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("order_id", order_id);

        Network.getOrderApi().getRefundInfo(user_id, order_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }


    Observer<RefundBean> observer = new Observer<RefundBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            dimissDialog();
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(RefundBean data) {
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
                View view = inflate.inflate(R.layout.refund_good_item, null);
                TextView tvStore = (TextView) view.findViewById(R.id.tvStore);
                TextView tvPhone = (TextView) view.findViewById(R.id.tvPhone);
                TextView descriptionTv = (TextView) view.findViewById(R.id.descriptionTv);
                TextView tvTotal = (TextView) view.findViewById(R.id.tvTotal);
                TextView tvGold = (TextView) view.findViewById(R.id.tvGold);
                ImageView imageIv = (ImageView) view.findViewById(R.id.imageIv);

                tvStore.setText(data.getData().getStore_name());
                tvPhone.setText(data.getData().getTel());
                descriptionTv.setText(data.getGoods().get(j).getGoods_name());
                tvTotal.setText("数量: " + data.getGoods().get(j).getQuantity());
                tvGold.setText("¥" + data.getData().getOrder_amount());
                Glide.with(getApplicationContext()).load(data.getGoods().get(j).getGoods_image()).into(imageIv);

                linGoods.addView(view);
            }

            //三楼
            amount = data.getData().getOrder_amount();
            tvAmount.setText("¥" + data.getData().getOrder_amount());


        }

    };


}
