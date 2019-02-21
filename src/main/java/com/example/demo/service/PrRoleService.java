package com.example.demo.service;

import com.example.demo.entity.PrRole;

import java.util.List;

public interface PrRoleService {

    List<PrRole> selectByEntity(PrRole record);
    
    PrRole selectById(Integer id);

    int insert(PrRole record);
    
    int deleteById(Integer id);
    
    int updateByEntity(PrRole record);
    
}