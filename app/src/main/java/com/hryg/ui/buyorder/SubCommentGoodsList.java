package com.hryg.ui.buyorder;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hryg.adapter.CommetGoodsListAdapter;
import com.hryg.base.BaseActivity;
import com.hryg.model.BuyOrderListData;
import com.kefanbufan.fengtimo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SubCommentGoodsList extends BaseActivity {


    @Bind(R.id.gridRv)
    RecyclerView gridRv;

    CommetGoodsListAdapter adapter = new CommetGoodsListAdapter();

    List<BuyOrderListData.DataBean> list;

    public static String order_id;


    @Bind(R.id.rlNodata)
    RelativeLayout rlNodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);
        ButterKnife.bind(this);
        getTopBar("商品评价列表");

        gridRv.setAdapter(adapter);
        gridRv.setLayoutManager(new LinearLayoutManager(SubCommentGoodsList.this));
        getData();

    }


    public void getData() {

        //订单 id
        order_id = getIntent().getExtras().get("order_id").toString();

        //订单里的商品列表
        Gson gson = new Gson();
        list = gson.fromJson(getIntent().getExtras().get("data").toString(),
                new TypeToken<List<BuyOrderListData.DataBean>>() {
                }.getType());

        adapter.setImages(list, SubCommentGoodsList.this);
    }


}
