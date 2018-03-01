package com.wan.grace.mvpapplication.bean;

/**
 * 通用数据接受类
 * DataModel
 * author wanchun
 * email 1596900283@qq.com
 * create 2018/3/1 16:33
 */
public class DataModel<T> {

    private String code;

    private String message;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
