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
    private Integer parentId;
    /*   */
    private String funcName;
    /*   */
    private String funcValue;
    /*   */
    private Date createTime;
    /*   */
    private Byte isDel;
    /*   */
    private String requestUrl;
    /* 权重  */
    private Integer weight;
    
    public Long getId() {
        return this.id;
    }
    public Integer getParentId() {
        return this.parentId;
    }
    public String getFuncName() {
        return this.funcName;
    }
    public String getFuncValue() {
        return this.funcValue;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    public Byte getIsDel() {
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
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }
    public void setFuncValue(String funcValue) {
        this.funcValue = funcValue;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
  
}