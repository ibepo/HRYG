package com.hryg.network.api;

import com.hryg.model.Category;
import com.hryg.model.HomeData;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;


public interface HomeApi {


    @FormUrlEncoded
    @POST("?app=default")
    Observable<HomeData> postHome(@FieldMap Map<String, Object> map);


    @GET("?app=category")
    Observable<Category> getCategory();


}
