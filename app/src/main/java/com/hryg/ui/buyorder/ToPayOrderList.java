package com.hryg.ui.buyorder;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.IRecyclerView;
import com.hryg.adapter.BuyOrderListAdapter;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.BuyOrderListData;
import com.hryg.network.Network;
import com.hryg.widget.LoadMoreFooterView;
import com.kefanbufan.fengtimo.R;

import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ToPayOrderList extends BaseActivity implements OnLoadMoreListener, OnRefreshListener {


    RelativeLayout rlNodata;
    private LoadMoreFooterView loadMoreFooterView;
    private IRecyclerView iRecyclerView;
    int page = 1;
    BuyOrderListAdapter adapter = new BuyOrderListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);
        ButterKnife.bind(this);
        getTopBar("待付款");

        rlNodata = (RelativeLayout) this.findViewById(R.id.rlNodata);
        iRecyclerView = (IRecyclerView) this.findViewById(R.id.iRecyclerView);
        iRecyclerView.setLayoutManager(new LinearLayoutManager(ToPayOrderList.this));
        iRecyclerView.setIAdapter(adapter);

        loadMoreFooterView = (LoadMoreFooterView) iRecyclerView.getLoadMoreFooterView();
        iRecyclerView.setOnRefreshListener(this);
        iRecyclerView.setOnLoadMoreListener(this);
//        iRecyclerView.post(new Runnable() {
//            @Override
//            public void run() {
//                iRecyclerView.setRefreshing(true);
//            }
//        });


    }

    @Override
    public void onRefresh() {
        page = 1;
        getData(page);
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        getData(page);
        if (loadMoreFooterView.canLoadMore() && adapter.getItemCount() > 0) {
            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        iRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                iRecyclerView.setRefreshing(true);
            }
        });
    }

    public void getData(int page) {
        Network.getOrderApi().getAllOrder(PathConfig.user_id, "11", page + "")
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
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(BuyOrderListData data) {

            iRecyclerView.setRefreshing(false);
            if (page == 1) {
                if (data.getData() != null) {
                    rlNodata.setVisibility(View.GONE);
                    adapter.setImages(data.getData(), ToPayOrderList.this);
                    page++;
                } else {
                    rlNodata.setVisibility(View.VISIBLE);
                }
            } else {
                if (data.getData() != null) {
                    page++;
                    adapter.addData(data.getData());
                } else {
                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
                }

            }

        }

    };


}
