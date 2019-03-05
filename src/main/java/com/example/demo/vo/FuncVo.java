package com.example.demo.vo;

import com.example.demo.entity.mymysql.PrFuncs;

/**
 * Created by dhf_ndm on 2019/2/27
 * the previous bug derived from the previous
 */
public class  FuncVo extends PrFuncs {
    Integer hasAuth;

    public Integer getHasAuth() {
        return hasAuth;
    }

    public void setHasAuth(Integer hasAuth) {
        this.hasAuth = hasAuth;
    }

}
