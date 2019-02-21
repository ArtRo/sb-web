package com.example.demo.service;

import com.example.demo.entity.PrRoleFuncRelation;

import java.util.List;

public interface PrRoleFuncRelationService {

    List<PrRoleFuncRelation> selectByEntity(PrRoleFuncRelation record);
    
    PrRoleFuncRelation selectById(Long id);

    int insert(PrRoleFuncRelation record);
    
    int deleteById(Long id);
    
    int updateByEntity(PrRoleFuncRelation record);
    
}