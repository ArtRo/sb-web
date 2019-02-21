package com.example.demo.dao;

import com.example.demo.entity.PrAdminRoleRelation;

import java.util.List;

public interface PrAdminRoleRelationDao {

    List<PrAdminRoleRelation> selectByEntity(PrAdminRoleRelation record);
    
    PrAdminRoleRelation selectById(Integer id);

    int insert(PrAdminRoleRelation record);
  
    int deleteById(Integer id);
    
    int updateByEntity(PrAdminRoleRelation record);
    
}