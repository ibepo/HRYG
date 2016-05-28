package com.hryg.network.api;

import com.hryg.model.Auth;
import com.hryg.model.ResultBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface RegisterApi {

    @FormUrlEncoded
    @POST("?app=register&act=sent_phone")
    Observable<ResultBean<Auth>> getAuth(@Field("mobile") String mobile);


    @FormUrlEncoded
    @POST("?app=register")
    Observable<ResultBean<Auth>> postRegister(@FieldMap Map<String, Object> map);
}
