package com.hryg.model;

import java.util.List;

/**
 * Created by kefanbufan on 16/5/31.
 */
public class CommentInfo {


    /**
     * code : 1
     * description : 获取成功
     * data : {"order_id":"21628","order_sn":"1608800326","type":"material","extension":"normal","seller_id":"19077","seller_name":"红包专区","buyer_id":"20446","buyer_name":"wangcb","buyer_email":"7706772017@qq.com","if_delete":"0","status":"40","add_time":"1459224951","payment_id":null,"payment_name":"易购通","payment_code":"sft","out_trade_sn":"","pay_time":"1459225357","pay_message":"","ship_time":"1459225642","invoice_no":"110","finished_time":"1459225691","goods_amount":"0.00","discount":"0.00","order_amount":"8.00","wuliubianhao":"","remark":"测试发货","evaluation_status":"0","evaluation_time":"0","anonymous":"0","postscript":"购买测试，请勿真是发货","pay_alter":"0","gold":"30.00","eprice":null,"get_gold":null,"wuliugongsi":"bangsongwuliu","apply_status":"0","xlgtag":"1","buyer_ip":"59.44.12.58","shouxufei":"0.00","guanglifei":"0.40","duobao":"1","sumtime":"0","Community_id":"","order_spec":null,"money_share":"0","if_hongbao":"1"}
     * goods : [{"goods_id":"136254","quantity":"1","goods_name":"大宝SOD蜜90ml清爽保湿/滋润/温和/润肤乳液 紧致肌肤","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_189/small_201603241326292895.png","price":"0.00","gold":"30.00","rec_id":"45632"}]
     */

    private int code;
    private String description;
    /**
     * order_id : 21628
     * order_sn : 1608800326
     * type : material
     * extension : normal
     * seller_id : 19077
     * seller_name : 红包专区
     * buyer_id : 20446
     * buyer_name : wangcb
     * buyer_email : 7706772017@qq.com
     * if_delete : 0
     * status : 40
     * add_time : 1459224951
     * payment_id : null
     * payment_name : 易购通
     * payment_code : sft
     * out_trade_sn :
     * pay_time : 1459225357
     * pay_message :
     * ship_time : 1459225642
     * invoice_no : 110
     * finished_time : 1459225691
     * goods_amount : 0.00
     * discount : 0.00
     * order_amount : 8.00
     * wuliubianhao :
     * remark : 测试发货
     * evaluation_status : 0
     * evaluation_time : 0
     * anonymous : 0
     * postscript : 购买测试，请勿真是发货
     * pay_alter : 0
     * gold : 30.00
     * eprice : null
     * get_gold : null
     * wuliugongsi : bangsongwuliu
     * apply_status : 0
     * xlgtag : 1
     * buyer_ip : 59.44.12.58
     * shouxufei : 0.00
     * guanglifei : 0.40
     * duobao : 1
     * sumtime : 0
     * Community_id :
     * order_spec : null
     * money_share : 0
     * if_hongbao : 1
     */

    private DataBean data;
    /**
     * goods_id : 136254
     * quantity : 1
     * goods_name : 大宝SOD蜜90ml清爽保湿/滋润/温和/润肤乳液 紧致肌肤
     * goods_image : http://www.hr1g.net/data/files/store_19077/goods_189/small_201603241326292895.png
     * price : 0.00
     * gold : 30.00
     * rec_id : 45632
     */

    private List<GoodsBean> goods;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class DataBean {
        private String order_id;
        private String order_sn;
        private String type;
        private String extension;
        private String seller_id;
        private String seller_name;
        private String buyer_id;
        private String buyer_name;
        private String buyer_email;
        private String if_delete;
        private String status;
        private String add_time;
        private Object payment_id;
        private String payment_name;
        private String payment_code;
        private String out_trade_sn;
        private String pay_time;
        private String pay_message;
        private String ship_time;
        private String invoice_no;
        private String finished_time;
        private String goods_amount;
        private String discount;
        private String order_amount;
        private String wuliubianhao;
        private String remark;
        private String evaluation_status;
        private String evaluation_time;
        private String anonymous;
        private String postscript;
        private String pay_alter;
        private String gold;
        private Object eprice;
        private Object get_gold;
        private String wuliugongsi;
        private String apply_status;
        private String xlgtag;
        private String buyer_ip;
        private String shouxufei;
        private String guanglifei;
        private String duobao;
        private String sumtime;
        private String Community_id;
        private Object order_spec;
        private String money_share;
        private String if_hongbao;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getSeller_name() {
            return seller_name;
        }

        public void setSeller_name(String seller_name) {
            this.seller_name = seller_name;
        }

        public String getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(String buyer_id) {
            this.buyer_id = buyer_id;
        }

        public String getBuyer_name() {
            return buyer_name;
        }

        public void setBuyer_name(String buyer_name) {
            this.buyer_name = buyer_name;
        }

        public String getBuyer_email() {
            return buyer_email;
        }

        public void setBuyer_email(String buyer_email) {
            this.buyer_email = buyer_email;
        }

        public String getIf_delete() {
            return if_delete;
        }

        public void setIf_delete(String if_delete) {
            this.if_delete = if_delete;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public Object getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(Object payment_id) {
            this.payment_id = payment_id;
        }

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }

        public String getPayment_code() {
            return payment_code;
        }

        public void setPayment_code(String payment_code) {
            this.payment_code = payment_code;
        }

        public String getOut_trade_sn() {
            return out_trade_sn;
        }

        public void setOut_trade_sn(String out_trade_sn) {
            this.out_trade_sn = out_trade_sn;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getPay_message() {
            return pay_message;
        }

        public void setPay_message(String pay_message) {
            this.pay_message = pay_message;
        }

        public String getShip_time() {
            return ship_time;
        }

        public void setShip_time(String ship_time) {
            this.ship_time = ship_time;
        }

        public String getInvoice_no() {
            return invoice_no;
        }

        public void setInvoice_no(String invoice_no) {
            this.invoice_no = invoice_no;
        }

        public String getFinished_time() {
            return finished_time;
        }

        public void setFinished_time(String finished_time) {
            this.finished_time = finished_time;
        }

        public String getGoods_amount() {
            return goods_amount;
        }

        public void setGoods_amount(String goods_amount) {
            this.goods_amount = goods_amount;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getWuliubianhao() {
            return wuliubianhao;
        }

        public void setWuliubianhao(String wuliubianhao) {
            this.wuliubianhao = wuliubianhao;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getEvaluation_status() {
            return evaluation_status;
        }

        public void setEvaluation_status(String evaluation_status) {
            this.evaluation_status = evaluation_status;
        }

        public String getEvaluation_time() {
            return evaluation_time;
        }

        public void setEvaluation_time(String evaluation_time) {
            this.evaluation_time = evaluation_time;
        }

        public String getAnonymous() {
            return anonymous;
        }

        public void setAnonymous(String anonymous) {
            this.anonymous = anonymous;
        }

        public String getPostscript() {
            return postscript;
        }

        public void setPostscript(String postscript) {
            this.postscript = postscript;
        }

        public String getPay_alter() {
            return pay_alter;
        }

        public void setPay_alter(String pay_alter) {
            this.pay_alter = pay_alter;
        }

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public Object getEprice() {
            return eprice;
        }

        public void setEprice(Object eprice) {
            this.eprice = eprice;
        }

        public Object getGet_gold() {
            return get_gold;
        }

        public void setGet_gold(Object get_gold) {
            this.get_gold = get_gold;
        }

        public String getWuliugongsi() {
            return wuliugongsi;
        }

        public void setWuliugongsi(String wuliugongsi) {
            this.wuliugongsi = wuliugongsi;
        }

        public String getApply_status() {
            return apply_status;
        }

        public void setApply_status(String apply_status) {
            this.apply_status = apply_status;
        }

        public String getXlgtag() {
            return xlgtag;
        }

        public void setXlgtag(String xlgtag) {
            this.xlgtag = xlgtag;
        }

        public String getBuyer_ip() {
            return buyer_ip;
        }

        public void setBuyer_ip(String buyer_ip) {
            this.buyer_ip = buyer_ip;
        }

        public String getShouxufei() {
            return shouxufei;
        }

        public void setShouxufei(String shouxufei) {
            this.shouxufei = shouxufei;
        }

        public String getGuanglifei() {
            return guanglifei;
        }

        public void setGuanglifei(String guanglifei) {
            this.guanglifei = guanglifei;
        }

        public String getDuobao() {
            return duobao;
        }

        public void setDuobao(String duobao) {
            this.duobao = duobao;
        }

        public String getSumtime() {
            return sumtime;
        }

        public void setSumtime(String sumtime) {
            this.sumtime = sumtime;
        }

        public String getCommunity_id() {
            return Community_id;
        }

        public void setCommunity_id(String Community_id) {
            this.Community_id = Community_id;
        }

        public Object getOrder_spec() {
            return order_spec;
        }

        public void setOrder_spec(Object order_spec) {
            this.order_spec = order_spec;
        }

        public String getMoney_share() {
            return money_share;
        }

        public void setMoney_share(String money_share) {
            this.money_share = money_share;
        }

        public String getIf_hongbao() {
            return if_hongbao;
        }

        public void setIf_hongbao(String if_hongbao) {
            this.if_hongbao = if_hongbao;
        }
    }

    public static class GoodsBean {
        private String goods_id;
        private String quantity;
        private String goods_name;
        private String goods_image;
        private String price;
        private String gold;
        private String rec_id;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
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

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
        }
    }
}
