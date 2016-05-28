package com.hryg.network.api;

import com.hryg.model.OrderConfirmData;
import com.hryg.model.OrderListData;
import com.hryg.model.PostOrderData;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;


public interface OrderApi {

    //得到订单中的基础信息
    @GET("?app=order&store_id=19077&goods=cart")
    Observable<OrderConfirmData> getOrderConfirmData(@Query("user_id") String id);

    //商品清单
    @GET("?app=order&goods=cart&store_id=19077&act=get_goods_infos")
    Observable<OrderListData> getOrderList(@Query("user_id") String id);

    //提交订单
    @FormUrlEncoded
    @POST("?app=order&goods=cart&store_id=19077")
    Observable<PostOrderData> postOrder(@FieldMap Map<String, Object> map);


    @Multipart
    @POST("?app=order&goods=cart&store_id=19077")
    Observable<PostOrderData> postOrder2(@PartMap Map<String, RequestBody> map);






}