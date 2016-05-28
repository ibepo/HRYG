package com.hryg.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hryg.adapter.ShoppingCarAdapter;
import com.hryg.base.BaseFragment;
import com.hryg.base.PathConfig;
import com.hryg.model.ShoppingCar;
import com.hryg.network.Network;
import com.kefanbufan.fengtimo.R;

import net.frakbot.jumpingbeans.JumpingBeans;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ShoppingCarFragment extends BaseFragment {

    @Bind(R.id.rlNodata)
    RelativeLayout rlNodata;
    @Bind(R.id.tvLoading)
    TextView tvLoading;
    private RecyclerView gridRv;
    ShoppingCarAdapter adapter = new ShoppingCarAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shoppingcar_fragment, null, false);

        getData();
        ButterKnife.bind(this, view);

        //下方商品列表
        gridRv = (RecyclerView) view.findViewById(R.id.gridRv);
        gridRv.setAdapter(adapter);
        gridRv.setLayoutManager(new LinearLayoutManager(getContext()));

        rlNodata.setVisibility(View.GONE);

        JumpingBeans.with(tvLoading)
                .appendJumpingDots()
                .build();

        return view;
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
            rlNodata.setVisibility(View.VISIBLE);
            tvLoading.setVisibility(View.GONE);

        }

        @Override
        public void onNext(ShoppingCar data) {

            tvLoading.setVisibility(View.GONE);
            if (data.getData().size() > 0) {
                rlNodata.setVisibility(View.GONE);
            } else {
                rlNodata.setVisibility(View.VISIBLE);
            }

            adapter.setImages(data.getData(), getContext());


        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
