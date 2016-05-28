package com.hryg.network.api;

import com.hryg.model.AddressListData;
import com.hryg.model.ResultBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface AddressApi {


    @FormUrlEncoded
    @POST("?app=my_address&act=index")
    Observable<AddressListData> getAlllist(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("?app=my_address&act=add")
    Observable<ResultBean> add(@FieldMap Map<String, Object> map);


}