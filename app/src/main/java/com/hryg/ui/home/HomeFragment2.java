package com.hryg.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
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
import com.kefanbufan.fengtimo.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HomeFragment2 extends BaseFragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {


    private SliderLayout mDemoSlider;
    private RecyclerView gridRv;
    GoodGridListAdapter adapter = new GoodGridListAdapter();
    RecyclerViewHeader header;
    int page = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, null, false);

        //幻灯片
        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());

        //下方商品列表
        gridRv = (RecyclerView) view.findViewById(R.id.gridRv);
        gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        gridRv.setAdapter(adapter);

        header = (RecyclerViewHeader) view.findViewById(R.id.header);
        header.attachTo(gridRv);

        getData(page);

        return view;
    }

    //幻灯片
    public View getHeadBanner() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.home_banner, gridRv, false);
        mDemoSlider = (SliderLayout) headView.findViewById(R.id.slider);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
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
        mDemoSlider.setDuration(4000);
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
            if (page == 1) {
                initBanner(homeData.getData());
                adapter.setImages(homeData.getGoods_list(), getContext());
                page++;
            } else {
                page++;
                adapter.addData(homeData.getGoods_list());

            }

        }
    };


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
