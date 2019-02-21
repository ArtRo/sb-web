package com.example.demo.service.impl;

import com.example.demo.dao.PrFuncsDao;
import com.example.demo.entity.PrFuncs;
import com.example.demo.service.PrFuncsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PrFuncsServiceImpl implements PrFuncsService {

    @Autowired
    private PrFuncsDao prFuncsDao;
    
    @Override
    public List<PrFuncs> selectByEntity(PrFuncs record) {
        List<PrFuncs> result = prFuncsDao.selectByEntity(record);
        return result;
    }  
    
    @Override
    public PrFuncs selectById(Long id) {
        PrFuncs result = prFuncsDao.selectById(id);
        return result;
    }

    @Override
    public int insert(PrFuncs record) {
         return prFuncsDao.insert(record);
    }   
    
    @Override
    public int updateByEntity(PrFuncs record) {
        return prFuncsDao.updateByEntity(record);
    }

    @Override
    public List<PrFuncs> getPrfuncsByAdminId(Integer adminId) {
        return prFuncsDao.getFuncsByAdminId(adminId);
    }

    @Override
    public int deleteById(Long id) {
        return prFuncsDao.deleteById(id);
    }
    
}