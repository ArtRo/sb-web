package com.example.demo.service;

import com.example.demo.entity.mymysql.PrRole;
import com.example.demo.entity.mymysql.PrRoleExample;

import java.util.List;

public interface PrRoleService {

    List<PrRole> selectByEntity(PrRoleExample record);
    
    PrRole selectById(Integer id);

    int insert(PrRole record);
    
    int deleteById(Integer id);
    
    int updateByEntity(PrRole record);
    
}