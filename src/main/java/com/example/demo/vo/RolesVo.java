package com.example.demo.vo;

import com.example.demo.entity.mymysql.PrFuncs;
import com.example.demo.entity.mymysql.PrRole;

import java.util.List;

/**
 * Created by dhf_ndm on 2019/5/9
 * the previous bug derived from the previous
 */
public class RolesVo extends PrRole {

    List<PrFuncs> funcs;

    public List<PrFuncs> getFuncs() {
        return funcs;
    }

    public void setFuncs(List<PrFuncs> funcs) {
        this.funcs = funcs;
    }
}
