package com.hryg.network.api;

import com.hryg.model.LoginUserId;
import com.hryg.model.ResultBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface LoginApi {


    @FormUrlEncoded
    @POST("?app=login")
    Observable<ResultBean<LoginUserId>> postLogin(@FieldMap Map<String, Object> map);
}
