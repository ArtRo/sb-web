package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

/* 读取表结构信息 */
public class PrFuncs implements Serializable{
    
    public PrFuncs() {

    }
    
    /*   */
    private Long id;
    /*   */
    private Long parentId;
    /*   */
    private String name;
    /*   */
    private String value;
    /*   */
    private Date createTime;
    /*   */
    private Integer isDel;
    /*   */
    private String requestUrl;
    /* 权重  */
    private Integer weight;
    
    public Long getId() {
        return this.id;
    }
    public Long getParentId() {
        return this.parentId;
    }
    public String getName() {
        return this.name;
    }
    public String getValue() {
        return this.value;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    public Integer getIsDel() {
        return this.isDel;
    }
    public String getRequestUrl() {
        return this.requestUrl;
    }
    public Integer getWeight() {
        return this.weight;
    }
   
    public void setId(Long id) {
        this.id = id;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
  
}