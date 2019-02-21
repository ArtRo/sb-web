package com.example.demo.dao;

import com.example.demo.entity.PrRoleFuncRelation;

import java.util.List;

public interface PrRoleFuncRelationDao {

    List<PrRoleFuncRelation> selectByEntity(PrRoleFuncRelation record);
    
    PrRoleFuncRelation selectById(Long id);

    int insert(PrRoleFuncRelation record);
  
    int deleteById(Long id);
    
    int updateByEntity(PrRoleFuncRelation record);
    
}