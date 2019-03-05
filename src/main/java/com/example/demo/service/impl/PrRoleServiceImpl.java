package com.example.demo.service.impl;

import com.example.demo.dao.mymysql.PrRoleMapper;
import com.example.demo.entity.mymysql.PrRole;
import com.example.demo.entity.mymysql.PrRoleExample;
import com.example.demo.service.PrRoleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PrRoleServiceImpl implements PrRoleService {

    @Autowired
    private PrRoleMapper prRoleDao;
    
    @Override
    public List<PrRole> selectByEntity(PrRoleExample record) {
        List<PrRole> result = prRoleDao.selectByExample(record);
        return result;
    }  
    
    @Override
    public PrRole selectById(Integer id) {
        PrRole result = prRoleDao.selectByPrimaryKey(id);
        return result;
    }

    @Override
    public int insert(PrRole record) {
        record.setRoleStatus(1);
        record.setIsDel(1);
        prRoleDao.insert(record);
        return record.getId();
    }   
    
    @Override
    public int updateByEntity(PrRole record) {
        return prRoleDao.updateByPrimaryKeySelective(record);
    }    

    @Override
    public int deleteById(Integer id) {
        return prRoleDao.deleteByPrimaryKey(id);
    }
    
}