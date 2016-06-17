package com.hryg.ui.mine;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.hryg.adapter.GroupAdapter;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.PeiIdListBean;
import com.hryg.model.ResultBean4Rep;
import com.hryg.network.Network;
import com.kefanbufan.fengtimo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class IDGroupList extends BaseActivity {


    @Bind(R.id.gridRv)
    RecyclerView gridRv;

    @Bind(R.id.tvAction)
    TextView tvAction;

    GroupAdapter adapter = new GroupAdapter();
    String pei_id;
    public static List<String> IDList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_list);
        ButterKnife.bind(this);
        getTopBar("ID 列表");
        pei_id = getIntent().getExtras().get("pei_id").toString();
        gridRv.setAdapter(adapter);
        gridRv.setLayoutManager(new LinearLayoutManager(IDGroupList.this));

    }


    public void getData() {

        Network.getMineApi().getIdList(PathConfig.user_id, pei_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<PeiIdListBean> observer = new Observer<PeiIdListBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(PeiIdListBean data) {
            adapter.setImages(data.getData(), IDGroupList.this);

        }

    };

    @Override
    public void onResume() {
        super.onResume();
        getData();

    }

    @OnClick(R.id.tvAction)
    public void onClick() {
        String ids = "";
        for (String id : IDList) {
            ids = ids + id;
        }
        showDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("pei_id", pei_id);
        map.put("ids", ids);
        Network.getMineApi().postId(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);

    }

    Observer<ResultBean4Rep> observer2 = new Observer<ResultBean4Rep>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(ResultBean4Rep data) {
            dimissDialog();
            if (data.getCode() == 1) {
                ToastUtils.showSuperToastAlertGreen(IDGroupList.this, data.getDescription());
                finish();
            } else {
                ToastUtils.showSuperToastAlertGreen(IDGroupList.this, data.getDescription());
            }

        }

    };


}
