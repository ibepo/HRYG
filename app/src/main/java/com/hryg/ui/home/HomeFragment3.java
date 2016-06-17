package com.hryg.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.hryg.adapter.GoodGridListAdapter;
import com.hryg.base.BaseFragment;
import com.hryg.base.PathConfig;
import com.hryg.model.HomeData;
import com.hryg.network.Network;
import com.hryg.ui.search.Search;
import com.hryg.widget.LoadMoreFooterView;
import com.kefanbufan.fengtimo.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HomeFragment3 extends BaseFragment implements OnLoadMoreListener, OnRefreshListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {


    private LoadMoreFooterView loadMoreFooterView;
    private SliderLayout mDemoSlider;
    private IRecyclerView iRecyclerView;
    GoodGridListAdapter adapter = new GoodGridListAdapter();
    int page = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home4, null, false);


        //下方商品列表
        iRecyclerView = (IRecyclerView) view.findViewById(R.id.iRecyclerView);
        iRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        iRecyclerView.addHeaderView(getHeadBanner());
        iRecyclerView.setIAdapter(adapter);

        loadMoreFooterView = (LoadMoreFooterView) iRecyclerView.getLoadMoreFooterView();
        iRecyclerView.setOnRefreshListener(this);
        iRecyclerView.setOnLoadMoreListener(this);
        iRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                iRecyclerView.setRefreshing(true);
            }
        });

        ButterKnife.bind(this, view);
        return view;
    }


    //幻灯片
    public View getHeadBanner() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.home_banner, iRecyclerView, false);
        mDemoSlider = (SliderLayout) headView.findViewById(R.id.slider);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        LinearLayout linSearch = (LinearLayout) headView.findViewById(R.id.linSearch);
        linSearch.getBackground().setAlpha(100);
        linSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Search.class);
                getActivity().startActivity(intent);
            }
        });


        LinearLayout linMeiRong = (LinearLayout) headView.findViewById(R.id.linMeiRong);
        LinearLayout linDianQi = (LinearLayout) headView.findViewById(R.id.linDianQi);
        LinearLayout linRiHua = (LinearLayout) headView.findViewById(R.id.linRiHua);
        LinearLayout linYiFu = (LinearLayout) headView.findViewById(R.id.linYiFu);


        linMeiRong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Search.class);
                intent.putExtra("cate_id", "1321");
                getActivity().startActivity(intent);

            }
        });
        linDianQi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Search.class);
                intent.putExtra("cate_id", "1732");
                getActivity().startActivity(intent);

            }
        });
        linRiHua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Search.class);
                intent.putExtra("cate_id", "13634");
                getActivity().startActivity(intent);

            }
        });
        linYiFu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Search.class);
                intent.putExtra("cate_id", "1298");
                getActivity().startActivity(intent);

            }
        });


        return headView;
    }

    void getData(int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", PathConfig.user_id);
        map.put("page", page + "");
        Network.getHomeApi().postHome(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    void initBanner(List<HomeData.Data> list) {
        mDemoSlider.removeAllSliders();
        for (HomeData.Data data : list) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .image(data.getImg())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle()
//                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(2500);
        mDemoSlider.addOnPageChangeListener(this);
    }

    Observer<HomeData> observer = new Observer<HomeData>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(HomeData homeData) {
            iRecyclerView.setRefreshing(false);
            if (page == 1) {
                initBanner(homeData.getData());
                adapter.setImages(homeData.getGoods_list(), getContext());
                page++;
            } else {
                if (homeData.getGoods_list().size() > 0) {
                    page++;
                    adapter.addData(homeData.getGoods_list());
                } else {
                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
                }


            }

        }
    };


    @Override
    public void onRefresh() {
        page = 1;
        getData(page);
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        getData(page);
        if (loadMoreFooterView.canLoadMore() && adapter.getItemCount() > 0) {
            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
//        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
