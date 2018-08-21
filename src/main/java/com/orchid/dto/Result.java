package com.orchid.dto;

/**
 * Created by ljg on 2018/5/4.
 */
public class Result<T> {
    //返回消息状态码 0：异常，1：成功
    private Integer status;
    //返回消息提示信息
    private String msg;
    //返回数据内容
    private T info;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
