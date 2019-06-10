package com.example.demo.service.impl;

import com.example.demo.dao.mymysql.PrRoleFuncRelationMapper;
import com.example.demo.entity.mymysql.PrRoleFuncRelation;
import com.example.demo.service.PrRoleFuncRelationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PrRoleFuncRelationServiceImpl implements PrRoleFuncRelationService {

    @Autowired
    private PrRoleFuncRelationMapper prRoleFuncRelationDao;

    
    @Override
    public PrRoleFuncRelation selectById(Long id) {
        PrRoleFuncRelation result = prRoleFuncRelationDao.selectByPrimaryKey(id);
        return result;
    }

    @Override
    public int insert(PrRoleFuncRelation record) {
         return prRoleFuncRelationDao.insert(record);
    }   
    
    @Override
    public int updateByEntity(PrRoleFuncRelation record) {
        return prRoleFuncRelationDao.updateByPrimaryKeySelective(record);
    }    

    @Override
    public int deleteById(Long id) {
        return prRoleFuncRelationDao.deleteByPrimaryKey(id);
    }
    
}