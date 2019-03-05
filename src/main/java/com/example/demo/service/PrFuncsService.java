package com.example.demo.service;

import com.example.demo.entity.mymysql.PrFuncs;
import com.example.demo.entity.mymysql.PrFuncsExample;
import com.example.demo.vo.FuncVo;

import java.util.List;

public interface PrFuncsService {

    List<PrFuncs> selectByEntity(PrFuncsExample record);
    
    PrFuncs selectById(Long id);

    int insert(PrFuncs record);
    
    int deleteById(Long id);
    
    int updateByEntity(PrFuncs record);

    Integer insertByBatch(List<PrFuncs> prFuncs);

    List<FuncVo> getFuncsByAdminId(Integer adminId);

    Object editAuthByAdminId(Integer adminId, Long[] funcs);
}