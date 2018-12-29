package com.example.demo.util;

public enum InfoCode {

    SERVICE_ORRER(404,"服务器异常"),



    REQUEST_OK(200,"请求成功");

    InfoCode(Integer code, String desc) {
        this.code=code;
        this.desc =desc;
    };
    Integer code;
    String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
