package com.lybank.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    //状态码
    private String code;
    //描述
    private String msg;
    //数据
    private Object data;

    public Result() {

    }

    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code) {
        this.code = code;

    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result setObject(Object obj) {
        this.data = obj;
        return this;
    }

    public Result ResultSuccess() {
        this.code = ResultStatus.SUCCESS;
        this.msg = ResultStatus.SUCCESS_MSG;
        return this;
    }

    public Result ResultFail() {
        this.code = ResultStatus.FAIL;
        this.msg = ResultStatus.FAIL_MSG;
        return this;
    }

    public Result ResultFail(String code, String msg) {
        this.code = code + "";
        this.msg = msg;
        return this;
    }

    public String toJsonString() {
        Map<String, Object> rm = new HashMap<String, Object>();
        rm.put("code", code);
        rm.put("msg", msg);
        rm.put("data", data);

        return JSONObject.toJSONString(rm);
    }

}
