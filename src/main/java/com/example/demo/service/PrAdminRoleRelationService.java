package com.example.demo.service;

import com.example.demo.entity.mymysql.PrAdminRoleRelation;

public interface PrAdminRoleRelationService {

    
    PrAdminRoleRelation selectById(Integer id);

    int insert(PrAdminRoleRelation record);
    
    int deleteById(Integer id);
    
    int updateByEntity(PrAdminRoleRelation record);
    
}