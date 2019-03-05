package com.example.demo.rest.adminManage;

import com.example.demo.bo.PageQo;
import com.example.demo.entity.mymysql.PrRole;
import com.example.demo.entity.mymysql.PrRoleExample;
import com.example.demo.service.PrRoleService;
import com.example.demo.vo.PageVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dhf_ndm on 2019/3/4
 * the previous bug derived from the previous
 */
@Controller
@RequestMapping(name = "角色管理", value = "/roleManage")
public class RoleController {

    @Autowired
    PrRoleService roleService;

    @RequestMapping(name = "添加", value = "/add")
    @ResponseBody
    public Object addRole(@Validated @RequestBody PrRole prRole, BindingResult bindingResult) {
        return roleService.insert(prRole);
    }

    @RequestMapping(name = "修改", value = "/edit")
    @ResponseBody
    public Object editRole(@RequestBody PrRole prRole) {
        return roleService.updateByEntity(prRole);
    }

    @RequestMapping(name = "删除", value = "/del")
    @ResponseBody
    public Object delRole(Integer role_id) {
        return roleService.deleteById(role_id);
    }

    @RequestMapping(name = "角色列表", value = "/list")
    @ResponseBody
    public Object listRole(PageQo pageQo) {
        PrRoleExample prRole = new PrRoleExample();
        prRole.createCriteria().andIdEqualTo(1);
        Page<PrRole> rolePage = PageHelper.startPage(pageQo.getPageNum(), pageQo.getPageSize(), pageQo.getOrderBy());
        List<PrRole> prRoles = roleService.selectByEntity(prRole);
        return PageVo.build(rolePage);
    }

}
