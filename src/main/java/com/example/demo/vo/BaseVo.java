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

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
