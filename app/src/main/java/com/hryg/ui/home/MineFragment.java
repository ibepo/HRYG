package com.hryg.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hryg.base.BaseFragment;
import com.hryg.base.ToastUtils;
import com.hryg.model.Category;
import com.hryg.network.Network;
import com.hryg.ui.address.AddressList;
import com.kefanbufan.fengtimo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MineFragment extends BaseFragment {


    @Bind(R.id.linAddress)
    LinearLayout linAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_center, null, false);
        getData();
        ButterKnife.bind(this, view);


        return view;
    }


    void getData() {
        Network.getHomeApi().getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    Observer<Category> observer = new Observer<Category>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {

            ToastUtils.showSuperToastAlertGreen(getContext(), "连接服务器失败");
        }

        @Override
        public void onNext(Category category) {


        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.linAddress)
    public void onClick() {
        Intent intent = new Intent(getActivity(), AddressList.class);
        getActivity().startActivity(intent);

    }
}
