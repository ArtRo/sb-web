package com.example.demo.vo;

import com.example.demo.util.InfoCode;

import javax.sound.sampled.Line;

public class BaseVo {

    public BaseVo() {
        this(InfoCode.REQUEST_OK,null);
    }

    public BaseVo(InfoCode ic) {
        this(ic, null);
    }

    public BaseVo(Object data) {
        this(InfoCode.REQUEST_OK, data);
    }

    public BaseVo(InfoCode ic, Object data) {
        if (null != ic) {
            this.msg = ic.getDesc();
            this.code = ic.getCode();
        }
        if (null != data)
            this.data = data;
    }

    Object data;
    Integer code;
    String msg;

    public Object getData() {
        return data;
    }

    public BaseVo setData(Object data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public BaseVo setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseVo setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
