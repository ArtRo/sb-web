package com.example.demo.service.impl;

import com.example.demo.bo.FuncAndRoleBo;
import com.example.demo.dao.PrFuncsDao;
import com.example.demo.entity.PrFuncs;
import com.example.demo.service.PrFuncsService;
import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.util.InfoCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = ApplicationRunTimeExeption.class,propagation = Propagation.REQUIRED)
    public int insert(PrFuncs record) {
        int insert = prFuncsDao.insert(record);
        if(insert <= 0) throw new ApplicationRunTimeExeption(InfoCode.INSERT_DATA_ERROR);
        return Math.toIntExact(record.getId());
    }

    @Override
    public int updateByEntity(PrFuncs record) {
        return prFuncsDao.updateByEntity(record);
    }

    @Override
//    @Cacheable(value = "prFuncs")
    public List<PrFuncs> getPrfuncsByAdminId(Integer adminId) {
        return prFuncsDao.getPrfuncsByAdminId(adminId);
    }

    @Override
    @Transactional(rollbackFor = ApplicationRunTimeExeption.class,propagation = Propagation.REQUIRED)
    public Integer insertByBatch(List<PrFuncs> prFuncs) {
        Integer ints = prFuncsDao.insertByBatch(prFuncs);
        if(ints <= 0) throw new ApplicationRunTimeExeption(InfoCode.INSERT_DATA_ERROR);
        return ints;
    }

    @Override
    public List<FuncAndRoleBo> getFuncAndRoleByUrl(String requestUrl) {
        return prFuncsDao.getFuncAndRoleByUrl(requestUrl);
    }

    @Override
    public int deleteById(Long id) {
        return prFuncsDao.deleteById(id);
    }

}