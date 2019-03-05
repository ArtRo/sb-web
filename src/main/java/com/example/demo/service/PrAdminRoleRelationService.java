package com.example.demo.service;

import com.example.demo.entity.mymysql.PrAdminRoleRelation;
import com.example.demo.entity.mymysql.PrAdminRoleRelationExample;

import java.util.List;

public interface PrAdminRoleRelationService {

    List<PrAdminRoleRelation> selectByEntity(PrAdminRoleRelationExample record);
    
    PrAdminRoleRelation selectById(Integer id);

    int insert(PrAdminRoleRelation record);
    
    int deleteById(Integer id);
    
    int updateByEntity(PrAdminRoleRelation record);
    
}