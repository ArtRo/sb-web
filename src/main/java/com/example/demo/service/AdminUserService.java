package com.example.demo.service;

import com.example.demo.entity.mymysql.AdminUser;
import com.example.demo.entity.mymysql.AdminUserExample;

import java.util.List;

public interface AdminUserService {

    List<AdminUser> selectByEntity(AdminUserExample record);
    
    AdminUser selectById(Integer id);

    int insert(AdminUser record);
    
    int deleteById(Integer id);
    
    int updateByEntity(AdminUser record);

    AdminUser getAdminUserByUsername(String username);

    Object registerAdmin(AdminUser adminUser);

    Object loginAdmin(AdminUser adminUser);
}