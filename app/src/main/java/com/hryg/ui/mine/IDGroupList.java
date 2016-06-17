package com.hryg.ui.mine;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hryg.adapter.DownLineAdapter;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.IdListBean;
import com.hryg.network.Network;
import com.kefanbufan.fengtimo.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class IDGroupList extends BaseActivity {


    @Bind(R.id.gridRv)
    RecyclerView gridRv;
    DownLineAdapter adapter = new DownLineAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_list);
        ButterKnife.bind(this);
        getTopBar("分配ID");
        gridRv.setAdapter(adapter);
        gridRv.setLayoutManager(new LinearLayoutManager(IDGroupList.this));

    }


    public void getData() {

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        Network.getMineApi().getId2(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<IdListBean> observer = new Observer<IdListBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(IdListBean data) {
            adapter.setImages(data.getData(), IDGroupList.this);

        }

    };

    @Override
    public void onResume() {
        super.onResume();
        getData();

    }

}
