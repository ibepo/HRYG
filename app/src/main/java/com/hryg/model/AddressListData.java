package com.hryg.model;

import java.util.List;

/**
 * Created by kefanbufan on 16/5/27.
 */
public class AddressListData {


    /**
     * code : 1
     * description : 获取成功
     * data : [{"addr_id":"1643","consignee":"王春标","phone_tel":"15004043523","region_name":"中国\t天津市\t和平区","address":"111","if_show":"0"},{"addr_id":"1644","consignee":"111","phone_tel":"15004043523","region_name":"中国\t天津市\t河东区","address":"111","if_show":"0"},{"addr_id":"2855","consignee":"wangcb","phone_tel":"15004043523","region_name":"中国\t新疆维吾尔自治区\t乌鲁木齐市","address":"111","if_show":"0"},{"addr_id":"3505","consignee":"王春标","phone_tel":"15004043523","region_name":"辽宁省\t沈阳市\t皇姑区","address":"经典生活12-18-06","if_show":"0"}]
     */

    private int code;
    private String description;
    /**
     * addr_id : 1643
     * consignee : 王春标
     * phone_tel : 15004043523
     * region_name : 中国	天津市	和平区
     * address : 111
     * if_show : 0
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String addr_id;
        private String consignee;
        private String phone_tel;
        private String region_name;
        private String address;
        private String if_show;

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

        public String getPhone_tel() {
            return phone_tel;
        }

        public void setPhone_tel(String phone_tel) {
            this.phone_tel = phone_tel;
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

        public String getIf_show() {
            return if_show;
        }

        public void setIf_show(String if_show) {
            this.if_show = if_show;
        }
    }
}
