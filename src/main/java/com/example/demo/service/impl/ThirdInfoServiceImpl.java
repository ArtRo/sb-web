package com.example.demo.service.impl;

import com.example.demo.dao.ThirdInfoDao;
import com.example.demo.entity.ThirdInfo;
import com.example.demo.service.ThirdInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThirdInfoServiceImpl implements ThirdInfoService {

    @Autowired
    private ThirdInfoDao thirdInfoDao;

    @Override
    public List<ThirdInfo> selectByEntity(ThirdInfo record) {
        List<ThirdInfo> result = thirdInfoDao.selectByEntity(record);
        return result;
    }

    @Override
    @Cacheable(value = "thirdInfos",key="'id_'+#id")
    public ThirdInfo selectById(Long id) {
        System.out.println("load2");
        ThirdInfo result = thirdInfoDao.selectById(id);
        return result;
    }

    @Override
    public int insert(ThirdInfo record) {
        return thirdInfoDao.insert(record);
    }

    @Override
    public int updateByEntity(ThirdInfo record) {
        return thirdInfoDao.updateByEntity(record);
    }

    @Override
    public int deleteById(Long id) {
        return thirdInfoDao.deleteById(id);
    }

}