package com.hryg.network.api;

import com.hryg.model.GoodComment;
import com.hryg.model.GoodsData;
import com.hryg.model.ResultBean;
import com.hryg.model.ShoppingCar;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public interface GoodsApi {


    @GET("?app=goods&act=comments")
    Observable<GoodComment> getComment(@Query("id") String id, @Query("page") String page);


    @GET("?app=goods&act=get_specs")
    Observable<ResultBean<GoodsData>> getGoodsData(@Query("id") String id);


    @FormUrlEncoded
    @POST("?app=cart&act=add")
    Observable<ResultBean> addGoods(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("?app=cart&act=index")
    Observable<ShoppingCar> getShoppingCar(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("?app=cart&act=drop")
    Observable<ResultBean> dropGoods(@FieldMap Map<String, Object> map);


}