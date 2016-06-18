package com.hryg.ui.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hryg.adapter.OrderListAdapter;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.OrderListData;
import com.hryg.network.Network;
import com.kefanbufan.fengtimo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderList extends BaseActivity {


    @Bind(R.id.gridRv)
    RecyclerView gridRv;


    OrderListAdapter adapter = new OrderListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_list);
        ButterKnife.bind(this);
        getTopBar("商品清单");

        gridRv.setAdapter(adapter);
        gridRv.setLayoutManager(new LinearLayoutManager(OrderList.this));
        getData();

    }


    public void getData() {
        Network.getOrderApi().getOrderList(PathConfig.user_id)
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
            adapter.setImages(data.getData(), OrderList.this);

        }

    };


}
