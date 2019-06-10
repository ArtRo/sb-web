package com.example.demo.service;

import com.example.demo.entity.mymysql.AdminUser;

public interface AdminUserService {


    AdminUser selectById(Integer id);

    int insert(AdminUser record);
    
    int deleteById(Integer id);
    
    int updateByEntity(AdminUser record);

    AdminUser getAdminUserByUsername(String username);

    Object registerAdmin(AdminUser adminUser);

    Object loginAdmin(AdminUser adminUser);
}