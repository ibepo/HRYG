package com.hryg.ui.order;

import android.os.Bundle;

import com.hryg.adapter.OrderListAdapter;
import com.hryg.base.BaseActivity;
import com.hryg.base.ToastUtils;
import com.hryg.model.OrderListData;
import com.hryg.network.Network;
import com.kefanbufan.fengtimo.R;

import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PayType extends BaseActivity {


    OrderListAdapter adapter = new OrderListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_type);
        ButterKnife.bind(this);
        getTopBar("选择支付方式");

        getData();

    }


    public void getData() {
        Network.getOrderApi().getOrderList("20446")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<OrderListData> observer = new Observer<OrderListData>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(OrderListData data) {
//            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), data.getDescription());
            adapter.setImages(data.getData(), PayType.this);

        }

    };


}
