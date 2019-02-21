package com.example.demo.dao;

import com.example.demo.entity.PrFuncs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrFuncsDao {

    List<PrFuncs> selectByEntity(PrFuncs record);
    
    PrFuncs selectById(Long id);

    int insert(PrFuncs record);
  
    int deleteById(Long id);
    
    int updateByEntity(PrFuncs record);

    List<PrFuncs> getFuncsByAdminId(Integer adminId);
    
}