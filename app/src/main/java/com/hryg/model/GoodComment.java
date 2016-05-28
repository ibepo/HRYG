package com.hryg.model;

import java.util.List;

/**
 * Created by kefanbufan on 16/5/21.
 */
public class GoodComment {


    /**
     * code : 1
     * description : 注册成功
     * data : [{"buyer_id":"19573","buyer_name":"陈小晨","evaluation_time":"1453078277","comment":"","evaluation":"3","rec_id":"40991"},{"buyer_id":"20446","buyer_name":"wangcb","evaluation_time":"1444417777","comment":"","evaluation":"3","rec_id":"33583"},{"buyer_id":"45488","buyer_name":"小才","evaluation_time":"1443375038","comment":"","evaluation":"3","rec_id":"34942"},{"buyer_id":"45657","buyer_name":"赵鹏","evaluation_time":"1442366548","comment":"","evaluation":"3","rec_id":"34847"},{"buyer_id":"46330","buyer_name":"14341007845288","evaluation_time":"1443648867","comment":"","evaluation":"3","rec_id":"35722"},{"buyer_id":"47188","buyer_name":"果木木果","evaluation_time":"1443663271","comment":"","evaluation":"3","rec_id":"35166"},{"buyer_id":"47858","buyer_name":"14398806827478","evaluation_time":"1447722760","comment":"","evaluation":"3","rec_id":"37849"},{"buyer_id":"48595","buyer_name":"14427487456977","evaluation_time":"1455678376","comment":"","evaluation":"3","rec_id":"42825"},{"buyer_id":"50474","buyer_name":"14493031226241","evaluation_time":"1451868201","comment":"","evaluation":"3","rec_id":"40186"},{"buyer_id":"51058","buyer_name":"14503360038340","evaluation_time":"1453591844","comment":"","evaluation":"3","rec_id":"41317"}]
     */

    private int code;
    private String description;
    /**
     * buyer_id : 19573
     * buyer_name : 陈小晨
     * evaluation_time : 1453078277
     * comment :
     * evaluation : 3
     * rec_id : 40991
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
        private String buyer_id;
        private String buyer_name;
        private String evaluation_time;
        private String comment;
        private String evaluation;
        private String rec_id;

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

        public String getEvaluation_time() {
            return evaluation_time;
        }

        public void setEvaluation_time(String evaluation_time) {
            this.evaluation_time = evaluation_time;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getEvaluation() {
            return evaluation;
        }

        public void setEvaluation(String evaluation) {
            this.evaluation = evaluation;
        }

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
        }
    }
}
