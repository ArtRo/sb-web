package com.example.demo.rest.adminManage;

import com.example.demo.entity.AdminUser;
import com.example.demo.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dhf_ndm on 2019/2/22
 * the previous bug derived from the previous
 */
@Controller
@RequestMapping(value = "/admin", name = "管理人员控制器")
public class AdminController {

    @Autowired
    AdminUserService adminUserService;

    @RequestMapping(value = "/register", name = "注册", method = RequestMethod.POST)
    @ResponseBody
    public Object registerAdmin(@Validated @RequestBody AdminUser adminUser, BindingResult bindingResult) {
        return adminUserService.registerAdmin(adminUser);
    }

    @RequestMapping(value = "/login", name = "登陆", method = RequestMethod.POST)
    @ResponseBody
    public Object loginAdmin(@Validated @RequestBody AdminUser adminUser, BindingResult bindingResult) {
        return adminUserService.loginAdmin(adminUser);
    }
}
