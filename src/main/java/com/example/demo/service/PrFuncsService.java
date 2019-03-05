package com.example.demo.service;

import com.example.demo.bo.FuncAndRoleBo;
import com.example.demo.entity.mymysql.PrFuncs;
import com.example.demo.entity.mymysql.PrFuncsExample;

import java.util.List;

public interface PrFuncsService {

    List<PrFuncs> selectByEntity(PrFuncsExample record);
    
    PrFuncs selectById(Long id);

    int insert(PrFuncs record);
    
    int deleteById(Long id);
    
    int updateByEntity(PrFuncs record);

    List<PrFuncs> getPrfuncsByAdminId(Integer adminId);

    Integer insertByBatch(List<PrFuncs> prFuncs);

    List<FuncAndRoleBo> getFuncAndRoleByUrl(String requestUrl);
}