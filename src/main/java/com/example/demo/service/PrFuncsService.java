package com.example.demo.service;

import com.example.demo.entity.mymysql.PrFuncs;

import java.util.List;

public interface PrFuncsService {

    
    PrFuncs selectById(Long id);

    int insert(PrFuncs record);
    
    int deleteById(Long id);
    
    int updateByEntity(PrFuncs record);

    Integer insertByBatch(List<PrFuncs> prFuncs);

    List<PrFuncs> getFuncsByAdminId(Integer adminId);

    Object editAuthByAdminId(Integer adminId, Long[] funcs);

    List<PrFuncs> getModularAuthByUser(Integer adminId);
}