package com.example.demo.service;

import com.example.demo.entity.mymysql.PrRoleFuncRelation;

public interface PrRoleFuncRelationService {
    
    PrRoleFuncRelation selectById(Long id);

    int insert(PrRoleFuncRelation record);
    
    int deleteById(Long id);
    
    int updateByEntity(PrRoleFuncRelation record);
    
}