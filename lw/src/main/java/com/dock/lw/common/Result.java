package com.dock.lw.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private Integer code;
    private String msg;

    @JSONField(serialzeFeatures= {SerializerFeature.WriteMapNullValue})
    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(ResultCode resultCode, T data) {
        this(data);
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public static <T> Result<T> success(T data) {
        return new Result(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> fail(ResultCode resultCode, T data) {
        return new Result(resultCode, data);
    }

    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result(resultCode, null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
