package com.hryg.ui.buyorder;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.hryg.adapter.BuyOrderListAdapter;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.BuyOrderListData;
import com.hryg.network.Network;
import com.kefanbufan.fengtimo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SubCommentGoodsList extends BaseActivity {


    @Bind(R.id.gridRv)
    RecyclerView gridRv;


    BuyOrderListAdapter adapter = new BuyOrderListAdapter();
    @Bind(R.id.rlNodata)
    RelativeLayout rlNodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);
        ButterKnife.bind(this);
        getTopBar("待评价");

        gridRv.setAdapter(adapter);
        gridRv.setLayoutManager(new LinearLayoutManager(SubCommentGoodsList.this));
        getData();

    }


    public void getData() {
        showDialog();
        Network.getOrderApi().getCommentOrder(PathConfig.user_id, "40", "1", "0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<BuyOrderListData> observer = new Observer<BuyOrderListData>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            dimissDialog();
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(BuyOrderListData data) {
            dimissDialog();
            adapter.setImages(data.getData(), SubCommentGoodsList.this);
            if (data.getData() == null) {
                rlNodata.setVisibility(View.VISIBLE);
            } else {
                rlNodata.setVisibility(View.GONE);
            }

        }

    };


}
