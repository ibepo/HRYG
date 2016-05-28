package com.hryg.ui.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hryg.adapter.GoodsDetailCommentAdapter;
import com.hryg.base.BaseFragment;
import com.hryg.base.ToastUtils;
import com.hryg.model.GoodComment;
import com.hryg.network.Network;
import com.kefanbufan.fengtimo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class GoodsCommentFragment extends BaseFragment {
    String id;

    @Bind(R.id.RVcomment)
    RecyclerView RVcomment;

    GoodsDetailCommentAdapter goodsDetailCommentAdapter = new GoodsDetailCommentAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goods_comment_fragemnt, null, false);
        ButterKnife.bind(this, view);
        id = GoodDetails.id;
        RVcomment.setAdapter(goodsDetailCommentAdapter);
        RVcomment.setLayoutManager(new LinearLayoutManager(getContext()));
        getData();
        return view;
    }

    public void getData() {

        Network.getGoodsApi().getComment(id, "1").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    Observer<GoodComment> observer = new Observer<GoodComment>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {

            ToastUtils.showSuperToastAlertGreen(getContext(), "连接服务器失败.....");
        }

        @Override
        public void onNext(GoodComment goodComment) {
            goodsDetailCommentAdapter.setData(goodComment.getData());

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
