package com.hryg.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.hryg.adapter.ShoppingCarAdapter;
import com.hryg.base.BaseFragment;
import com.hryg.base.PathConfig;
import com.hryg.model.ShoppingCar;
import com.hryg.network.Network;
import com.kefanbufan.fengtimo.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ShoppingCarFragment extends BaseFragment implements OnRefreshListener {

    @Bind(R.id.rlNodata)
    RelativeLayout rlNodata;
    private IRecyclerView iRecyclerView;
    ShoppingCarAdapter adapter = new ShoppingCarAdapter();
    float subtotal;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shoppingcar_fragment, null, false);
        ButterKnife.bind(this, view);

        iRecyclerView = (IRecyclerView) view.findViewById(R.id.iRecyclerView);
        iRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        iRecyclerView.setIAdapter(adapter);
        iRecyclerView.setOnRefreshListener(this);
        iRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                iRecyclerView.setRefreshing(true);
            }
        });

        rlNodata.setVisibility(View.VISIBLE);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void onRefresh() {
        getData();
    }

    void getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        Network.getGoodsApi().getShoppingCar(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<ShoppingCar> observer = new Observer<ShoppingCar>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            iRecyclerView.setRefreshing(false);
        }

        @Override
        public void onNext(ShoppingCar data) {
            iRecyclerView.setRefreshing(false);
            if (data.getData() != null) {
                rlNodata.setVisibility(View.GONE);
                subtotal = 0;
                for (ShoppingCar.DataBean bean : data.getData()) {
                    subtotal = subtotal + Float.parseFloat(bean.getSubtotal());
                }
                adapter.setImages(data.getData(), subtotal, getContext());

            } else {
                rlNodata.setVisibility(View.VISIBLE);
                adapter.setImages(null, 0, getContext());
            }

        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
