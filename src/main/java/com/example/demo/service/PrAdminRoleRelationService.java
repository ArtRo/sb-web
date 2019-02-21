package com.example.demo.service;

import com.example.demo.entity.PrAdminRoleRelation;

import java.util.List;

public interface PrAdminRoleRelationService {

    List<PrAdminRoleRelation> selectByEntity(PrAdminRoleRelation record);
    
    PrAdminRoleRelation selectById(Integer id);

    int insert(PrAdminRoleRelation record);
    
    int deleteById(Integer id);
    
    int updateByEntity(PrAdminRoleRelation record);
    
}