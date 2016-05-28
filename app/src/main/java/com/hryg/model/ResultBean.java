package com.hryg.model;

/**
 * Created by kefanbufan on 16/5/17.
 */
public class ResultBean<T> {


    private int code;

    private String description;

    private T data;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
}
