
package com.hryg.network;


import com.hryg.base.PathConfig;
import com.hryg.network.api.AddressApi;
import com.hryg.network.api.GoodsApi;
import com.hryg.network.api.HomeApi;
import com.hryg.network.api.LoginApi;
import com.hryg.network.api.MineApi;
import com.hryg.network.api.OrderApi;
import com.hryg.network.api.RegisterApi;
import com.hryg.network.api.SearchApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {


    private static RegisterApi registerApi;
    private static LoginApi loginApi;
    private static HomeApi homeApi;
    private static SearchApi searchApi;
    private static GoodsApi goodsApi;
    private static OrderApi orderApi;
    private static AddressApi addressApi;
    private static MineApi mineApi;


    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    //注册
    public static RegisterApi getRegisterApi() {
        if (registerApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(PathConfig.Address)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            registerApi = retrofit.create(RegisterApi.class);
        }
        return registerApi;
    }

    //登录
    public static LoginApi getLoginApi() {
        if (loginApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(PathConfig.Address)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            loginApi = retrofit.create(LoginApi.class);
        }
        return loginApi;
    }

    //首页四大模块接口
    public static HomeApi getHomeApi() {
        if (homeApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(PathConfig.Address)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            homeApi = retrofit.create(HomeApi.class);
        }
        return homeApi;
    }

    //搜索页数据
    public static SearchApi getSearchApi() {
        if (searchApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(PathConfig.Address)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            searchApi = retrofit.create(SearchApi.class);
        }
        return searchApi;
    }


    //详情页数据
    public static GoodsApi getGoodsApi() {
        if (goodsApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(PathConfig.Address)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            goodsApi = retrofit.create(GoodsApi.class);
        }
        return goodsApi;
    }


    //订单页数据
    public static OrderApi getOrderApi() {
        if (orderApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(PathConfig.Address)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            orderApi = retrofit.create(OrderApi.class);
        }
        return orderApi;
    }


    //地址维护
    public static AddressApi getAddressApi() {
        if (addressApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(PathConfig.Address)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            addressApi = retrofit.create(AddressApi.class);
        }
        return addressApi;
    }

    //个人中心接口
    public static MineApi getMineApi() {
        if (mineApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(PathConfig.Address)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            mineApi = retrofit.create(MineApi.class);
        }
        return mineApi;
    }


}
