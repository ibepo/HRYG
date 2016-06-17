package com.hryg.ui.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.hryg.adapter.AddressListAdapter;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.AddressListData;
import com.hryg.network.Network;
import com.kefanbufan.fengtimo.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddressList extends BaseActivity {


    @Bind(R.id.gridRv)
    RecyclerView gridRv;


    AddressListAdapter adapter = new AddressListAdapter();
    @Bind(R.id.tvAdd)
    TextView tvAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_list);
        ButterKnife.bind(this);
        getTopBar("收货地址");

        gridRv.setAdapter(adapter);
        gridRv.setLayoutManager(new LinearLayoutManager(AddressList.this));
        getData();

    }


    public void getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);


        Network.getAddressApi().getAlllist(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<AddressListData> observer = new Observer<AddressListData>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(AddressListData data) {
            adapter.setImages(data.getData(), AddressList.this);

        }

    };

    @Override
    public void onResume() {
        super.onResume();
        getData();

    }

    @OnClick(R.id.tvAdd)
    public void onClick() {

        Intent intent2 = new Intent(this, AddressAdd.class);
        this.startActivity(intent2);
    }
}
