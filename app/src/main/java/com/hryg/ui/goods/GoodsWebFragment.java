package com.hryg.ui.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hryg.base.BaseFragment;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.GoodsData;
import com.hryg.model.ResultBean;
import com.hryg.network.Network;
import com.hryg.ui.MainAct;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.kefanbufan.fengtimo.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.next.slidebottompanel.SlideBottomPanel;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class GoodsWebFragment extends BaseFragment {

    String id;
    int stockLimit;
    String spec_id;
    String quantity;

    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.tvGOUWU)
    TextView tvGOUWU;
    @Bind(R.id.tvBUG)
    TextView tvBUG;
    @Bind(R.id.sbv)
    SlideBottomPanel sbv;
    @Bind(R.id.linBuy)
    LinearLayout linBuy;


    //交易 ui
    @Bind(R.id.tv_goods_name)
    TextView tvGoodsName;
    @Bind(R.id.tv_gold)
    TextView tvGold;
    @Bind(R.id.tv_stock)
    TextView tvStock;
    @Bind(R.id.ivJia)
    ImageView ivJia;
    @Bind(R.id.tvNum)
    TextView tvNum;
    @Bind(R.id.ivJian)
    ImageView ivJian;
    @Bind(R.id.ivGoods)
    ImageView ivGoods;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_menu, null, false);
        ButterKnife.bind(this, view);
        id = GoodDetails.id;
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://www.hr1g.net/index_ios.php?app=goods&act=index&id=" + id);
        initView();
        getData();
        return view;
    }

    void initView() {

        tvNum.setText("1");


        RxView.clicks(ivJia).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                int i = Integer.parseInt(tvNum.getText().toString());
                i++;
                tvNum.setText(i + "");
            }
        });

        RxView.clicks(ivJian).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                int i = Integer.parseInt(tvNum.getText().toString());
                i--;
                tvNum.setText(i + "");
            }
        });

        RxTextView.textChanges(tvNum).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence c) {

                if (Integer.parseInt(c.toString()) > 1) {
                    ivJian.setClickable(true);
                } else {
                    ivJian.setClickable(false);
                }

                if (Integer.parseInt(c.toString()) == stockLimit) {
                    ivJia.setClickable(false);
                } else {
                    ivJia.setClickable(true);
                }

                quantity = c.toString();

            }
        });


    }


    public void getData() {

        Network.getGoodsApi().getGoodsData(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    Observer<ResultBean<GoodsData>> observer = new Observer<ResultBean<GoodsData>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
//            ToastUtils.showSuperToastAlertGreen(getContext(), "连接服务器失败");
        }

        @Override
        public void onNext(ResultBean<GoodsData> data) {
            tvGoodsName.setText(data.getData().getGoods_name());
            tvGold.setText(data.getData().getGold() + "红包兑换");
            tvStock.setText("库存" + data.getData().getStock() + "件");
            Glide.with(getActivity())
                    .load(data.getData().getImages())
                    .into(ivGoods);

            //
            spec_id = data.getData().getSpec_id();
            stockLimit = Integer.parseInt(data.getData().getStock());
            if (stockLimit == 1) {
                ivJia.setClickable(false);
            }

        }
    };


    public void addGoods() {


        GoodDetails goodDetails = (GoodDetails) getActivity();
        goodDetails.showDialog();

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("spec_id", spec_id);
        map.put("quantity", quantity);
        map.put("discount", 0);


        Network.getGoodsApi().addGoods(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);

    }


    Observer<ResultBean> observer2 = new Observer<ResultBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            GoodDetails goodDetails = (GoodDetails) getActivity();
            goodDetails.dimissDialog();
            ToastUtils.showSuperToastAlertGreen(getContext(), "连接服务器失败");
        }

        @Override
        public void onNext(ResultBean data) {


            ToastUtils.showSuperToastAlertGreen(getContext(), data.getDescription());
            GoodDetails goodDetails = (GoodDetails) getActivity();
            goodDetails.dimissDialog();
            goodDetails.shark();
            goodDetails.finish();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.tvGOUWU, R.id.tvBUG, R.id.linBuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linBuy:
                sbv.displayPanel();
                break;
            case R.id.tvGOUWU:
                addGoods();

                break;
            case R.id.tvBUG:
                addGoods();
                MainAct.goShopCar();
                break;

        }
    }
}
