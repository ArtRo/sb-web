package com.example.demo.service.impl;

import com.example.demo.dao.custom.FuncAndRoleMapper;
import com.example.demo.dao.mymysql.PrRoleMapper;
import com.example.demo.entity.mymysql.PrRole;
import com.example.demo.service.PrRoleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PrRoleServiceImpl implements PrRoleService {

    @Autowired
    private PrRoleMapper prRoleDao;

    @Autowired
    private FuncAndRoleMapper funcAndRoleMapper;
    
    @Override
    public PrRole selectById(Integer id) {
        PrRole result = prRoleDao.selectByPrimaryKey(id);
        return result;
    }

    @Override
    public int insert(PrRole record) {
        record.setRoleStatus(1);
        record.setIsDel(1);
        prRoleDao.insertSelective(record);
        return record.getId();
    }   
    
    @Override
    public int updateByEntity(PrRole record) {
        return prRoleDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<PrRole> selectByState(Integer state) {
        return funcAndRoleMapper.selectByState(state);
    }

    @Override
    public int deleteById(Integer id) {
        return prRoleDao.deleteByPrimaryKey(id);
    }
    
}