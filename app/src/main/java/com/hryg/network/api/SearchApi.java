package com.hryg.network.api;

import com.hryg.model.SearchData;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface SearchApi {


    @FormUrlEncoded
    @POST("?app=search")
    Observable<SearchData> postSearch(@FieldMap Map<String, Object> map);
}
