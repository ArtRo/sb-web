package com.example.demo.bo;

import com.example.demo.entity.PrFuncs;

/**
 * Created by dhf_ndm on 2019/2/27
 * the previous bug derived from the previous
 */
public class FuncAndRoleBo extends PrFuncs {
    Integer prId;

    String roleName;

    public Integer getPrId() {
        return prId;
    }

    public void setPrId(Integer prId) {
        this.prId = prId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
