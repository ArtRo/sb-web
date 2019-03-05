package com.example.demo.service;

import com.example.demo.entity.mymysql.PrRoleFuncRelation;
import com.example.demo.entity.mymysql.PrRoleFuncRelationExample;

import java.util.List;

public interface PrRoleFuncRelationService {

    List<PrRoleFuncRelation> selectByEntity(PrRoleFuncRelationExample record);
    
    PrRoleFuncRelation selectById(Long id);

    int insert(PrRoleFuncRelation record);
    
    int deleteById(Long id);
    
    int updateByEntity(PrRoleFuncRelation record);
    
}