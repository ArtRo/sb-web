package com.example.demo.dao;

import com.example.demo.entity.PrRole;

import java.util.List;

public interface PrRoleDao {

    List<PrRole> selectByEntity(PrRole record);
    
    PrRole selectById(Integer id);

    int insert(PrRole record);
  
    int deleteById(Integer id);
    
    int updateByEntity(PrRole record);
    
}