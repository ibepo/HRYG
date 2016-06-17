package com.hryg.network.api;


import com.hryg.model.IdListBean;
import com.hryg.model.MineData;
import com.hryg.model.MineDetailBean;
import com.hryg.model.PeiIdListBean;
import com.hryg.model.PicListBean;
import com.hryg.model.ResultBean4Rep;
import com.hryg.model.ReviewListBean;

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

public interface MineApi {


    //个人中心获取信息
    @FormUrlEncoded
    @POST("?app=member")
    Observable<MineData> getMineData(@FieldMap Map<String, Object> map);

    //个人信息详情
    @GET("?app=member")
    Observable<MineDetailBean> getMineDetail(@Query("act") String act, @Query("user_id") String id);

    //修改个人信息
    @FormUrlEncoded
    @POST("?app=member&act=profile_edit")
    Observable<ResultBean4Rep> modifyDetail(@FieldMap Map<String, Object> map);

    //申请成为商家
    @FormUrlEncoded
    @POST("?app=red_card&act=index")
    Observable<ResultBean4Rep> applyBusiness(@FieldMap Map<String, Object> map);

    //红包充值(编号充值)
    @FormUrlEncoded
    @POST("?app=cashier&act=recharge_gold")
    Observable<ResultBean4Rep> rechargeByNumber(@FieldMap Map<String, Object> map);


    //获取图片列表
    @GET("?app=red_card&act=upload_img")
    Observable<PicListBean> getPicList(@Query("user_id") String id);

    //审核列表
    @GET("?app=red_card&act=examine_store")
    Observable<ReviewListBean> getReviewList(@Query("user_id") String id);


    //上传多张图片
    @Multipart
    @POST("?app=red_card&act=upload_img")
    Observable<ResultBean4Rep> submitPic(@PartMap Map<String, RequestBody> map);


    //审核
    @FormUrlEncoded
    @POST("?app=red_card&act=examine_store")
    Observable<ResultBean4Rep> review(@FieldMap Map<String, Object> map);

    //得到id分组
    @GET("?app=red_card&act=distribution_info")
    Observable<PeiIdListBean> getIdList(@Query("user_id") String id, @Query("pei_id") String pie_id);


    //分配ID 组用的代理商列表
    @FormUrlEncoded
    @POST("?app=red_card&act=distribution_list")
    Observable<IdListBean> getId2(@FieldMap Map<String, Object> map);


    //提交id 组的分配请求
    @FormUrlEncoded
    @POST("?app=red_card&act=distribution_info")
    Observable<ResultBean4Rep> postId(@FieldMap Map<String, Object> map);


}
