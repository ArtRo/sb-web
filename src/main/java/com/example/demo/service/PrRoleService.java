package com.example.demo.service;

import com.example.demo.entity.mymysql.PrRole;

import java.util.List;

public interface PrRoleService {

    PrRole selectById(Integer id);

    int insert(PrRole record);
    
    int deleteById(Integer id);
    
    int updateByEntity(PrRole record);

    List<PrRole> selectByState(Integer i);
}