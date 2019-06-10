package com.example.demo.service.impl;

import com.example.demo.dao.mymysql.PrAdminRoleRelationMapper;
import com.example.demo.entity.mymysql.PrAdminRoleRelation;
import com.example.demo.service.PrAdminRoleRelationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PrAdminRoleRelationServiceImpl implements PrAdminRoleRelationService {

    @Autowired
    private PrAdminRoleRelationMapper prAdminRoleRelationDao;

    
    @Override
    public PrAdminRoleRelation selectById(Integer id) {
        PrAdminRoleRelation result = prAdminRoleRelationDao.selectByPrimaryKey(id);
        return result;
    }

    @Override
    public int insert(PrAdminRoleRelation record) {
         return prAdminRoleRelationDao.insert(record);
    }   
    
    @Override
    public int updateByEntity(PrAdminRoleRelation record) {
        return prAdminRoleRelationDao.updateByPrimaryKeySelective(record);
    }    

    @Override
    public int deleteById(Integer id) {
        return prAdminRoleRelationDao.deleteByPrimaryKey(id);
    }
    
}