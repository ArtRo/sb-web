package com.example.demo.service.impl;

import com.example.demo.dao.AdminUserDao;
import com.example.demo.entity.AdminUser;
import com.example.demo.service.AdminUserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;
    
    @Override
    public List<AdminUser> selectByEntity(AdminUser record) {
        List<AdminUser> result = adminUserDao.selectByEntity(record);
        return result;
    }  
    
    @Override
    public AdminUser selectById(Long id) {
        AdminUser result = adminUserDao.selectById(id);
        return result;
    }

    @Override
    public int insert(AdminUser record) {
         return adminUserDao.insert(record);
    }   
    
    @Override
    public int updateByEntity(AdminUser record) {
        return adminUserDao.updateByEntity(record);
    }

    @Override
    public AdminUser getAdminUserByUsername(String username) {
        return adminUserDao.getAdminUserByUsername(username);
    }

    @Override
    public int deleteById(Long id) {
        return adminUserDao.deleteById(id);
    }
    
}