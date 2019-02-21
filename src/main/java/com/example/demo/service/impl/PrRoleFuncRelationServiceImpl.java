package com.example.demo.service.impl;

import com.example.demo.dao.PrRoleFuncRelationDao;
import com.example.demo.entity.PrRoleFuncRelation;
import com.example.demo.service.PrRoleFuncRelationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PrRoleFuncRelationServiceImpl implements PrRoleFuncRelationService {

    @Autowired
    private PrRoleFuncRelationDao prRoleFuncRelationDao;
    
    @Override
    public List<PrRoleFuncRelation> selectByEntity(PrRoleFuncRelation record) {
        List<PrRoleFuncRelation> result = prRoleFuncRelationDao.selectByEntity(record);
        return result;
    }  
    
    @Override
    public PrRoleFuncRelation selectById(Long id) {
        PrRoleFuncRelation result = prRoleFuncRelationDao.selectById(id);
        return result;
    }

    @Override
    public int insert(PrRoleFuncRelation record) {
         return prRoleFuncRelationDao.insert(record);
    }   
    
    @Override
    public int updateByEntity(PrRoleFuncRelation record) {
        return prRoleFuncRelationDao.updateByEntity(record);
    }    

    @Override
    public int deleteById(Long id) {
        return prRoleFuncRelationDao.deleteById(id);
    }
    
}