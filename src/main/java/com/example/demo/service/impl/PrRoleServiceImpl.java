package com.example.demo.service.impl;

import com.example.demo.dao.PrRoleDao;
import com.example.demo.entity.PrRole;
import com.example.demo.service.PrRoleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PrRoleServiceImpl implements PrRoleService {

    @Autowired
    private PrRoleDao prRoleDao;
    
    @Override
    public List<PrRole> selectByEntity(PrRole record) {
        List<PrRole> result = prRoleDao.selectByEntity(record);
        return result;
    }  
    
    @Override
    public PrRole selectById(Integer id) {
        PrRole result = prRoleDao.selectById(id);
        return result;
    }

    @Override
    public int insert(PrRole record) {
         return prRoleDao.insert(record);
    }   
    
    @Override
    public int updateByEntity(PrRole record) {
        return prRoleDao.updateByEntity(record);
    }    

    @Override
    public int deleteById(Integer id) {
        return prRoleDao.deleteById(id);
    }
    
}