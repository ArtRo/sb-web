package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

/* 读取表结构信息 */
public class PrRoleFuncRelation implements Serializable{
    
    public PrRoleFuncRelation() {

    }
    
    /*   */
    private Long id;
    /*   */
    private Integer roleId;
    /*   */
    private Long funcId;
    /*   */
    private Date createTime;
    
    public Long getId() {
        return this.id;
    }
    public Integer getRoleId() {
        return this.roleId;
    }
    public Long getFuncId() {
        return this.funcId;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
   
    public void setId(Long id) {
        this.id = id;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public void setFuncId(Long funcId) {
        this.funcId = funcId;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
  
}