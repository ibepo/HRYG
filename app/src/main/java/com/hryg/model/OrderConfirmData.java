package com.hryg.model;

import java.util.List;

/**
 * Created by kefanbufan on 16/5/26.
 */
public class OrderConfirmData {


    /**
     * code : 1
     * description : 获取成功
     * address : [{"addr_id":"1643","consignee":"王春标","region_id":"12548","region_name":"中国\t天津市\t和平区","address":"111","phone_tel":"15004043523"}]
     * data : [{"goods_id":"133701","goods_name":"皓齿健 芨效全优120g牙膏","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_172/small_201601121546122244.jpg"},{"goods_id":"135808","goods_name":"云涛纯棉毛巾柔软亲肤洗脸面巾 彩色擦脸巾洗脸毛巾正品32*70cm","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_167/small_201603071532473740.png"},{"goods_id":"137418","goods_name":"飘柔洗发水家庭护理芦荟长效止痒滋润洗发露190ml","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_8/small_201605041423283658.png"},{"goods_id":"137422","goods_name":"Gillette吉列威锋旋转双层剃须刀片男士手动刮胡刀头3片装无刀架","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_79/small_201605041517596501.png"}]
     * shipping : [{"logist_fees":32,"type":"express","name":"快递"}]
     * amount : 0
     * amount_gold : 18
     */

    private int code;
    private String description;
    private int amount;
    private int amount_gold;
    /**
     * addr_id : 1643
     * consignee : 王春标
     * region_id : 12548
     * region_name : 中国	天津市	和平区
     * address : 111
     * phone_tel : 15004043523
     */

    private List<AddressBean> address;
    /**
     * goods_id : 133701
     * goods_name : 皓齿健 芨效全优120g牙膏
     * goods_image : http://www.hr1g.net/data/files/store_19077/goods_172/small_201601121546122244.jpg
     */

    private List<DataBean> data;
    /**
     * logist_fees : 32
     * type : express
     * name : 快递
     */

    private List<ShippingBean> shipping;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount_gold() {
        return amount_gold;
    }

    public void setAmount_gold(int amount_gold) {
        this.amount_gold = amount_gold;
    }

    public List<AddressBean> getAddress() {
        return address;
    }

    public void setAddress(List<AddressBean> address) {
        this.address = address;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<ShippingBean> getShipping() {
        return shipping;
    }

    public void setShipping(List<ShippingBean> shipping) {
        this.shipping = shipping;
    }

    public static class AddressBean {
        private String addr_id;
        private String consignee;
        private String region_id;
        private String region_name;
        private String address;
        private String phone_tel;

        public String getAddr_id() {
            return addr_id;
        }

        public void setAddr_id(String addr_id) {
            this.addr_id = addr_id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getRegion_id() {
            return region_id;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone_tel() {
            return phone_tel;
        }

        public void setPhone_tel(String phone_tel) {
            this.phone_tel = phone_tel;
        }
    }

    public static class DataBean {
        private String goods_id;
        private String goods_name;
        private String goods_image;

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

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }
    }

    public static class ShippingBean {
        private int logist_fees;
        private String type;
        private String name;

        public int getLogist_fees() {
            return logist_fees;
        }

        public void setLogist_fees(int logist_fees) {
            this.logist_fees = logist_fees;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
