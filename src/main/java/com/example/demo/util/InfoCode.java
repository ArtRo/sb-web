package com.example.demo.util;

public enum InfoCode {

    ACCOUNT_OR_PASSWORD_ERROR(401,"账号密码异常"),

    INSERT_DATA_ERROR(407,"添加数据异常"),

    USER_HAS_EXIST(408,"该用户已存在"),

    SERVICE_ORRER(404,"服务器异常"),

    FILE_NOT_FOUNT(405,"文件未找到"),

    FILE_UPLOAD_FDFS_FAIL(405,"文件上传到fdfs失败"),

    FILE_DELETE_FDFS_FAIL(406,"fdfs文件删除失败"),

    PARAMETER_ERROR(633,"参数异常"),


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
