package com.example.demo.service.impl;

import com.example.demo.bo.FuncAndRoleBo;
import com.example.demo.dao.PrFuncsDao;
import com.example.demo.entity.PrFuncs;
import com.example.demo.service.PrFuncsService;
import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.util.InfoCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Transactional(rollbackFor = ApplicationRunTimeExeption.class)
    public int insert(PrFuncs record) {
        int insert = prFuncsDao.insert(record);
        if (insert <= 0) throw new ApplicationRunTimeExeption(InfoCode.INSERT_DATA_ERROR);
        return insert;
    }

    @Override
    public int updateByEntity(PrFuncs record) {
        return prFuncsDao.updateByEntity(record);
    }

    @Override
    public List<PrFuncs> getPrfuncsByAdminId(Integer adminId) {
        return prFuncsDao.getPrfuncsByAdminId(adminId);
    }

    @Override
    @Transactional(rollbackFor = ApplicationRunTimeExeption.class)
    public int[] insertByBatch(List<PrFuncs> prFuncs) {
        int[] ints = prFuncsDao.insertByBatch(prFuncs);
        if(!Integer.valueOf(prFuncs.size()).equals(ints.length)){
            throw new ApplicationRunTimeExeption(InfoCode.INSERT_DATA_ERROR);
        }
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