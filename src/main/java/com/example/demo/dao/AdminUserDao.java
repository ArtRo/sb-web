package com.example.demo.dao;

import com.example.demo.entity.AdminUser;

import java.util.List;

public interface AdminUserDao {

    List<AdminUser> selectByEntity(AdminUser record);
    
    AdminUser selectById(Long id);

    int insert(AdminUser record);
  
    int deleteById(Long id);
    
    int updateByEntity(AdminUser record);

    AdminUser getAdminUserByUsername(String username);

    int updateEntityByUsername(AdminUser record);
    
}