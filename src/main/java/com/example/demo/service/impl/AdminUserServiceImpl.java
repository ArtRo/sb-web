package com.example.demo.service.impl;

import com.example.demo.dao.custom.AdminMapper;
import com.example.demo.dao.mymysql.AdminUserMapper;
import com.example.demo.entity.mymysql.AdminUser;
import com.example.demo.service.AdminUserService;
import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.util.InfoCode;
import com.example.demo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public AdminUser selectById(Integer id) {
        AdminUser result = adminUserMapper.selectByPrimaryKey(id);
        return result;
    }

    @Override
    public int insert(AdminUser record) {
        return adminUserMapper.insertSelective(record);
    }

    @Override
    public int updateByEntity(AdminUser record) {
        return adminUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public AdminUser getAdminUserByUsername(String username) {
        List<AdminUser> adminUsers = adminMapper.selectByUserName(username);
        return adminUsers.size() == 1 ? adminUsers.get(0) : null;
    }

    @Override
    public int deleteById(Integer id) {
        return adminUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Object registerAdmin(AdminUser adminUser) {
        if (!isRegistered(adminUser.getUsername())) {
            String password = adminUser.getPassword();
            adminUser.setPassword(passwordEncoder.encode(password));
            int insert = adminUserMapper.insertSelective(adminUser);
            if (insert > 0) {
                //两种逻辑 直接跳转进入或者到登陆页面登陆
                return insert;
            }
            throw new ApplicationRunTimeExeption(InfoCode.INSERT_DATA_ERROR);
        }
        throw new ApplicationRunTimeExeption(InfoCode.USER_HAS_EXIST);
    }

    @Override
    public Object loginAdmin(AdminUser adminUser) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(adminUser.getUsername(), passwordEncoder.encode(adminUser.getPassword()));
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetails userDetails = userDetailsService.loadUserByUsername(adminUser.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("tokenHeader", tokenHeader);
        map.put("tokenHead", tokenHead);
        return map;
    }

    //一般的话 手机和邮箱发送验证码 在注册时候要检验两次是否被注册 另外限制发送短信和邮箱验证码的时间 防止
    private boolean isRegistered(String username) {
        boolean isRegistered = true;
        AdminUser adminUserByUsername = getAdminUserByUsername(username);
        if (null == adminUserByUsername) {
            isRegistered = false;
        }
        return isRegistered;
    }


}