package com.hryg.network.api;

import com.hryg.model.AddressListData;
import com.hryg.model.AddressModifyData;
import com.hryg.model.ModifymResult;
import com.hryg.model.ResultBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public interface AddressApi {

    //获取地址列表
    @FormUrlEncoded
    @POST("?app=my_address&act=index")
    Observable<AddressListData> getAlllist(@FieldMap Map<String, Object> map);

    //新增地址
    @FormUrlEncoded
    @POST("?app=my_address&act=add")
    Observable<ResultBean> add(@FieldMap Map<String, Object> map);

    //获取详细地址
    @GET("?app=my_address&act=edit")
    Observable<AddressModifyData> getAddressInfo(@Query("user_id") String user_id, @Query("addr_id") String addr_id);


    //修改地址
    @FormUrlEncoded
    @POST("?app=my_address&act=edit")
    Observable<ModifymResult> modifyAddress(@FieldMap Map<String, Object> map);


    //删除地址
    @FormUrlEncoded
    @POST("?app=my_address&act=drop")
    Observable<ModifymResult> dropAddress(@FieldMap Map<String, Object> map);

}