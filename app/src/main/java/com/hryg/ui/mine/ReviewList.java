package com.hryg.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hryg.adapter.ReviewListAdapter;
import com.hryg.base.BaseActivity;
import com.hryg.base.PathConfig;
import com.hryg.base.ToastUtils;
import com.hryg.model.ReviewListBean;
import com.hryg.network.Network;
import com.kefanbufan.fengtimo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReviewList extends BaseActivity {


    @Bind(R.id.gridRv)
    RecyclerView gridRv;
    ReviewListAdapter adapter = new ReviewListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_list);
        ButterKnife.bind(this);
        getTopBar("商家审核");
        gridRv.setAdapter(adapter);
        gridRv.setLayoutManager(new LinearLayoutManager(ReviewList.this));

    }


    public void getData() {


        Network.getMineApi().getReviewList(PathConfig.user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<ReviewListBean> observer = new Observer<ReviewListBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            ToastUtils.showSuperToastAlertGreen(getApplicationContext(), "连接服务器失败");
        }

        @Override
        public void onNext(ReviewListBean data) {
            adapter.setImages(data.getData(), ReviewList.this);

        }

    };

    @Override
    public void onResume() {
        super.onResume();
        getData();

    }

}
