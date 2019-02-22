package com.example.demo.service;

import com.example.demo.entity.AdminUser;
import com.example.demo.vo.BaseVo;

import java.util.List;

public interface AdminUserService {

    List<AdminUser> selectByEntity(AdminUser record);
    
    AdminUser selectById(Long id);

    int insert(AdminUser record);
    
    int deleteById(Long id);
    
    int updateByEntity(AdminUser record);

    AdminUser getAdminUserByUsername(String username);

    Object registerAdmin(AdminUser adminUser);

    Object loginAdmin(AdminUser adminUser);
}