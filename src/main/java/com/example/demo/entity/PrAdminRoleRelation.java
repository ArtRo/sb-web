package com.example.demo.entity;

import java.io.Serializable;

/* 读取表结构信息 */
public class PrAdminRoleRelation implements Serializable{
    
    public PrAdminRoleRelation() {

    }
    
    /*   */
    private Integer id;
    /*   */
    private Integer adminId;
    /*   */
    private Integer roleId;
    
    public Integer getId() {
        return this.id;
    }
    public Integer getAdminId() {
        return this.adminId;
    }
    public Integer getRoleId() {
        return this.roleId;
    }
   
    public void setId(Integer id) {
        this.id = id;
    }
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
  
}