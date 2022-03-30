package com.lybank.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable {
    private static final long serialVersionUID = -2451181602707854145L;
    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
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

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result setObject(Object obj) {
        this.data = obj;
        return this;
    }

    /**
     * 成功返回
     *
     * @return
     */
    public Result ResultSuccess() {
        this.code = Constant.ResultStatus_SUCCESS;
        this.msg = Constant.ResultStatus_SUCCESS_MSG;
        return this;
    }

    /**
     * 失败返回
     *
     * @return
     */
    public Result ResultFail() {
        this.code = Constant.ResultStatus_FAIL;
        this.msg = Constant.ResultStatus_FAIL_MSG;
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
