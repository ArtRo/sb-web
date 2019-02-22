package com.example.demo.entity;

import com.example.demo.validator.NotNull;

import java.io.Serializable;
import java.util.Date;

/* 读取表结构信息 */
public class AdminUser implements Serializable{
    
    public AdminUser() {

    }
    
    /*   */
    private Integer id;
    /*   */
    @NotNull
    private String username;
    /*   */
    @NotNull
    private String password;
    /* 状态 0 禁用 1 启用  */
    private Integer status;
    /* 头像  */
    private String image;
    /* 签名  */
    private String signature;
    /* 是否删除 -1 删除 1 存在  */
    private Integer isDel;
    /*   */
    private String nickname;
    /*   */
    private Date createTime;
    /*   */
    private Long updateTime;
    
    public Integer getId() {
        return this.id;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public Integer getStatus() {
        return this.status;
    }
    public String getImage() {
        return this.image;
    }
    public String getSignature() {
        return this.signature;
    }
    public Integer getIsDel() {
        return this.isDel;
    }
    public String getNickname() {
        return this.nickname;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    public Long getUpdateTime() {
        return this.updateTime;
    }
   
    public void setId(Integer id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
  
}