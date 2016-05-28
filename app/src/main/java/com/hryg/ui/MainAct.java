package com.hryg.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.githang.viewpagerindicator.IconPagerAdapter;
import com.githang.viewpagerindicator.IconTabPageIndicator;
import com.hryg.base.BaseFragment;
import com.hryg.ui.home.CategoryFragment;
import com.hryg.ui.home.HomeFragment;
import com.hryg.ui.home.MineFragment;
import com.hryg.ui.home.ShoppingCarFragment;
import com.kefanbufan.fengtimo.R;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainAct extends FragmentActivity {
    SweetAlertDialog pDialog;
    private ViewPager mViewPager;
    private IconTabPageIndicator mIndicator;

    public void showDialog() {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("加载中");
        pDialog.show();
        pDialog.setCancelable(false);

    }

    public void dimissDialog() {
        pDialog.dismissWithAnimation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainact);
        initViews();
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mIndicator = (IconTabPageIndicator) findViewById(R.id.indicator);
        List<BaseFragment> fragments = initFragments();
        FragmentAdapter adapter = new FragmentAdapter(fragments, getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mIndicator.setViewPager(mViewPager);
    }

    private List<BaseFragment> initFragments() {
        List<BaseFragment> fragments = new ArrayList<BaseFragment>();

//        HomeFragment userFragment = new HomeFragment();
//        userFragment.setTitle("首页");
//        userFragment.setIconId(R.drawable.tab_home);
//        fragments.add(userFragment);
//
//        CategoryFragment categoryFragment = new CategoryFragment();
//        categoryFragment.setTitle("分类");
//        categoryFragment.setIconId(R.drawable.tab_paihang);
//        fragments.add(categoryFragment);
//
//
//        ShoppingCarFragment shoppingCarFragment = new ShoppingCarFragment();
//        shoppingCarFragment.setTitle("购物车");
//        shoppingCarFragment.setIconId(R.drawable.tab_dairy);
//        fragments.add(shoppingCarFragment);
//
//
//        MineFragment mineFragment = new MineFragment();
//        mineFragment.setTitle("我的");
//        mineFragment.setIconId(R.drawable.tab_my);
//        fragments.add(mineFragment);


        HomeFragment userFragment = new HomeFragment();
        userFragment.setIconId(R.drawable.tab_hr_home);
        fragments.add(userFragment);

        CategoryFragment categoryFragment = new CategoryFragment();
        categoryFragment.setIconId(R.drawable.tab_hr_type);
        fragments.add(categoryFragment);


        ShoppingCarFragment shoppingCarFragment = new ShoppingCarFragment();
        shoppingCarFragment.setIconId(R.drawable.tab_hr_shopcar);
        fragments.add(shoppingCarFragment);


        MineFragment mineFragment = new MineFragment();
        mineFragment.setIconId(R.drawable.tab_hr_mine);
        fragments.add(mineFragment);

        return fragments;
    }

    class FragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
        private List<BaseFragment> mFragments;

        public FragmentAdapter(List<BaseFragment> fragments, FragmentManager fm) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getIconResId(int index) {
            return mFragments.get(index).getIconId();
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragments.get(position).getTitle();
        }
    }

}
