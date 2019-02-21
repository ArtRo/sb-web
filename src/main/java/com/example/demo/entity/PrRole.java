package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

/* 读取表结构信息 */
public class PrRole implements Serializable{
    
    public PrRole() {

    }
    
    /*   */
    private Integer id;
    /*   */
    private String name;
    /*   */
    private Integer weight;
    /*   */
    private Date createTime;
    /* 0 禁用 1 启用  */
    private Byte status;
    /* 描述  */
    private String description;
    
    public Integer getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public Integer getWeight() {
        return this.weight;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    public Byte getStatus() {
        return this.status;
    }
    public String getDescription() {
        return this.description;
    }
   
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public void setStatus(Byte status) {
        this.status = status;
    }
    public void setDescription(String description) {
        this.description = description;
    }
  
}