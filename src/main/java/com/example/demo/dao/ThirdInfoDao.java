package com.example.demo.dao;



import com.example.demo.entity.ThirdInfo;

import java.util.List;

public interface ThirdInfoDao {

    List<ThirdInfo> selectByEntity(ThirdInfo record);
    
    ThirdInfo selectById(Long id);

    int insert(ThirdInfo record);
  
    int deleteById(Long id);
    
    int updateByEntity(ThirdInfo record);
    
}