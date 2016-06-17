package com.hryg.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hryg.adapter.CategoryBigTypeAdapter;
import com.hryg.adapter.CategorySmallTypeAdapter;
import com.hryg.adapter.SearchGridListAdapter;
import com.hryg.adapter.SearchListListAdapter;
import com.hryg.base.BaseFragment;
import com.hryg.model.Category;
import com.hryg.network.Network;
import com.hryg.ui.search.Search;
import com.kefanbufan.fengtimo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class CategoryFragment extends BaseFragment {


    CategoryBigTypeAdapter categoryBigTypeAdapter = new CategoryBigTypeAdapter();
    public static CategorySmallTypeAdapter categorySmallTypeAdapter = new CategorySmallTypeAdapter();

    SearchGridListAdapter adapter = new SearchGridListAdapter();
    SearchListListAdapter adapter2 = new SearchListListAdapter();

    @Bind(R.id.linShow)
    LinearLayout linShow;
    @Bind(R.id.rlShow)
    RelativeLayout rlShow;
    @Bind(R.id.gridRv)
    RecyclerView gridRv;
    @Bind(R.id.gridRv2)
    RecyclerView gridRv2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, null, false);
        getData();
        ButterKnife.bind(this, view);

        gridRv.setAdapter(categoryBigTypeAdapter);
        gridRv.setLayoutManager(new LinearLayoutManager(getContext()));


        gridRv2.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        gridRv2.setAdapter(categorySmallTypeAdapter);

        return view;
    }


    Observer<Category> observer = new Observer<Category>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
//            ToastUtils.showSuperToastAlertGreen(getContext(), "连接服务器失败");
        }

        @Override
        public void onNext(Category category) {
            categoryBigTypeAdapter.setData(category.getData(), getContext());
            categorySmallTypeAdapter.setImages(category.getData().get(0).getChildren(), getContext());
            categoryBigTypeAdapter.setAllData(category);

        }
    };


    void getData() {
        Network.getHomeApi().getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.rlShow)
    public void onClick() {
        Intent intent = new Intent(getActivity(), Search.class);
        getActivity().startActivity(intent);
    }
}
