package com.example.demo.service.impl;

import com.example.demo.dao.PrAdminRoleRelationDao;
import com.example.demo.entity.PrAdminRoleRelation;
import com.example.demo.service.PrAdminRoleRelationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PrAdminRoleRelationServiceImpl implements PrAdminRoleRelationService {

    @Autowired
    private PrAdminRoleRelationDao prAdminRoleRelationDao;
    
    @Override
    public List<PrAdminRoleRelation> selectByEntity(PrAdminRoleRelation record) {
        List<PrAdminRoleRelation> result = prAdminRoleRelationDao.selectByEntity(record);
        return result;
    }  
    
    @Override
    public PrAdminRoleRelation selectById(Integer id) {
        PrAdminRoleRelation result = prAdminRoleRelationDao.selectById(id);
        return result;
    }

    @Override
    public int insert(PrAdminRoleRelation record) {
         return prAdminRoleRelationDao.insert(record);
    }   
    
    @Override
    public int updateByEntity(PrAdminRoleRelation record) {
        return prAdminRoleRelationDao.updateByEntity(record);
    }    

    @Override
    public int deleteById(Integer id) {
        return prAdminRoleRelationDao.deleteById(id);
    }
    
}