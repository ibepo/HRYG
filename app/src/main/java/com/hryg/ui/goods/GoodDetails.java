package com.hryg.ui.goods;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.hryg.base.BaseActivity;
import com.kefanbufan.fengtimo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GoodDetails extends BaseActivity {

    @Bind(android.R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.ivBack)
    ImageView ivBack;


    static String id;
    @Bind(R.id.ivGouwu)
    ImageView ivGouwu;


    public void shark() {

        YoYo.with(Techniques.Shake)
                .duration(700)
                .playOn(ivGouwu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_details);
        id = getIntent().getExtras().getString("id");
        ButterKnife.bind(this);


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new GoodsWebFragment();
                    case 1:
                        return new GoodsCommentFragment();

                    default:
                        return new GoodsWebFragment();

                }
            }


            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {

                switch (position) {
                    case 0:
                        return "详情";
                    case 1:
                        return "评价";

                    default:
                        return "详情";
                }

            }

        });

        tabLayout.setupWithViewPager(viewPager);


    }

    @OnClick(R.id.ivBack)
    public void onClick() {
        finish();
    }
}
