package com.hryg.network.api;

import com.hryg.model.BuyOrderListData;
import com.hryg.model.CommentInfo;
import com.hryg.model.OrderConfirmData;
import com.hryg.model.OrderDetailBean;
import com.hryg.model.OrderListData;
import com.hryg.model.PostOrderData;
import com.hryg.model.RefundBean;
import com.hryg.model.ResultBean;
import com.hryg.model.ResultBean4Rep;
import com.hryg.model.TypePayData;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    //获得支付方式
    @GET("?app=cashier&order_cashier=1")
    Observable<TypePayData> getTypeData(@Query("user_id") String user_id, @Query("order_id") String order_id, @Query("store_id") String store_id);

    //余额支付
    @FormUrlEncoded
    @POST("?app=my_money&act=payment_android")
    Observable<ResultBean4Rep> payByBalance(@FieldMap Map<String, Object> map);


    //!++++++++++现金完成后的订单+++++++++++++!

    //获取全部订单
    @GET("?app=buyer_order")
    Observable<BuyOrderListData> getAllOrder(@Query("user_id") String user_id, @Query("type") String type, @Query("page") String page);


    //获取评论订单
    @GET("?app=buyer_order")
    Observable<BuyOrderListData> getCommentOrder(@Query("user_id") String user_id, @Query("type") String type, @Query("page") String page, @Query("evaluation_status") String evaluation_status);

    //获取退换货订单
    @GET("?app=buyer_order")
    Observable<BuyOrderListData> getChangetOrder(@Query("user_id") String user_id,  @Query("page") String page, @Query("apply_status") String apply_status);


    //删除订单
    @FormUrlEncoded
    @POST("?app=buyer_order&act=order_delete")
    Observable<ResultBean> dropOrder(@FieldMap Map<String, Object> map);

    //确认收货
    @FormUrlEncoded
    @POST("?app=buyer_order&act=confirm_order")
    Observable<ResultBean4Rep> confirmOrder(@FieldMap Map<String, Object> map);

    //获取订单评价页面信息
    @GET("?app=buyer_order&act=evaluate")
    Observable<CommentInfo> getCommentInfo(@Query("user_id") String user_id, @Query("order_id") String order_id);


    //获取订单详情
    @FormUrlEncoded
    @POST("?app=buyer_order&act=order_info")
    Observable<OrderDetailBean> getOrderDetail(@FieldMap Map<String, Object> map);

    //提交评价
    @FormUrlEncoded
    @POST("?app=buyer_order&act=evaluate_post")
    Observable<ResultBean4Rep> submitComment(@FieldMap Map<String, Object> map);


    //取消订单
    @FormUrlEncoded
    @POST("?app=buyer_order&act=cancel_order")
    Observable<ResultBean4Rep> cancelOrder(@FieldMap Map<String, Object> map);


    //获取退货详情
    @GET("?app=buyer_order&act=apply_refund")
    Observable<RefundBean> getRefundInfo(@Query("user_id") String user_id, @Query("order_id") String order_id);


    //申请退款
    @FormUrlEncoded
    @POST("?app=buyer_order&act=apply_refund_post")
    Observable<ResultBean4Rep> refund(@FieldMap Map<String, Object> map);


}