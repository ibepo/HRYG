package com.hryg.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kefanbufan on 16/6/3.
 */
public class RefundBean {


    /**
     * code : 1
     * description : 获取成功
     * data : {"order_id":"22342","order_sn":"1614729996","add_time":"1464316198","status":"20","store_name":"红包专区","order_amount":"30.00","tel":"15004043523"}
     * goods : [{"goods_id":"137299","goods_name":"坚美JM-9010吉兔大号可撕型粘毛滚粘毛器粘尘器除尘器","price":"0.00","gold":"19.00","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_186/small_201604110853064383.jpg","quantity":"1"},{"goods_id":"137417","goods_name":"Breeze/清风 御本草系列草本杀菌湿巾 40片","price":"0.00","gold":"30.00","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_142/small_201605041412228538.png","quantity":"1"}]
     * reason : {"1":"大小尺寸不符","2":"材质、面料不符","3":"工艺/手艺问题","4":"颜色、款式、图案与描述不符","5":"发货问题","6":"服务/态度","7":"不喜欢/穿着效果不好","8":"拍错/多拍","9":"其他"}
     */

    private String code;
    private String description;
    /**
     * order_id : 22342
     * order_sn : 1614729996
     * add_time : 1464316198
     * status : 20
     * store_name : 红包专区
     * order_amount : 30.00
     * tel : 15004043523
     */

    private DataBean data;
    /**
     * 1 : 大小尺寸不符
     * 2 : 材质、面料不符
     * 3 : 工艺/手艺问题
     * 4 : 颜色、款式、图案与描述不符
     * 5 : 发货问题
     * 6 : 服务/态度
     * 7 : 不喜欢/穿着效果不好
     * 8 : 拍错/多拍
     * 9 : 其他
     */

    private ReasonBean reason;
    /**
     * goods_id : 137299
     * goods_name : 坚美JM-9010吉兔大号可撕型粘毛滚粘毛器粘尘器除尘器
     * price : 0.00
     * gold : 19.00
     * goods_image : http://www.hr1g.net/data/files/store_19077/goods_186/small_201604110853064383.jpg
     * quantity : 1
     */

    private List<GoodsBean> goods;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ReasonBean getReason() {
        return reason;
    }

    public void setReason(ReasonBean reason) {
        this.reason = reason;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class DataBean {
        private String order_id;
        private String order_sn;
        private String add_time;
        private String status;
        private String store_name;
        private String order_amount;
        private String tel;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }

    public static class ReasonBean {
        @SerializedName("1")
        private String value1;
        @SerializedName("2")
        private String value2;
        @SerializedName("3")
        private String value3;
        @SerializedName("4")
        private String value4;
        @SerializedName("5")
        private String value5;
        @SerializedName("6")
        private String value6;
        @SerializedName("7")
        private String value7;
        @SerializedName("8")
        private String value8;
        @SerializedName("9")
        private String value9;

        public String getValue1() {
            return value1;
        }

        public void setValue1(String value1) {
            this.value1 = value1;
        }

        public String getValue2() {
            return value2;
        }

        public void setValue2(String value2) {
            this.value2 = value2;
        }

        public String getValue3() {
            return value3;
        }

        public void setValue3(String value3) {
            this.value3 = value3;
        }

        public String getValue4() {
            return value4;
        }

        public void setValue4(String value4) {
            this.value4 = value4;
        }

        public String getValue5() {
            return value5;
        }

        public void setValue5(String value5) {
            this.value5 = value5;
        }

        public String getValue6() {
            return value6;
        }

        public void setValue6(String value6) {
            this.value6 = value6;
        }

        public String getValue7() {
            return value7;
        }

        public void setValue7(String value7) {
            this.value7 = value7;
        }

        public String getValue8() {
            return value8;
        }

        public void setValue8(String value8) {
            this.value8 = value8;
        }

        public String getValue9() {
            return value9;
        }

        public void setValue9(String value9) {
            this.value9 = value9;
        }
    }

    public static class GoodsBean {
        private String goods_id;
        private String goods_name;
        private String price;
        private String gold;
        private String goods_image;
        private String quantity;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }
}
