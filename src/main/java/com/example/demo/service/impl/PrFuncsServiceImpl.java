package com.example.demo.service.impl;

import com.example.demo.dao.custom.FuncAndRoleMapper;
import com.example.demo.dao.mymysql.PrFuncsMapper;
import com.example.demo.dao.mymysql.PrRoleFuncRelationMapper;
import com.example.demo.entity.mymysql.PrFuncs;
import com.example.demo.entity.mymysql.PrRoleFuncRelation;
import com.example.demo.service.PrFuncsService;
import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.util.InfoCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrFuncsServiceImpl implements PrFuncsService {

    @Autowired
    private PrFuncsMapper prFuncsDao;

    @Autowired
    private FuncAndRoleMapper funcAndRoleMapper;

    @Autowired
    PrRoleFuncRelationMapper roleFuncRelationMapper;

    @Override
    public PrFuncs selectById(Long id) {
        PrFuncs result = prFuncsDao.selectByPrimaryKey(id);
        return result;
    }

    @Override
    @Transactional(rollbackFor = ApplicationRunTimeExeption.class, propagation = Propagation.REQUIRED)
    public int insert(PrFuncs record) {
        int insert = prFuncsDao.insertSelective(record);
        if (insert <= 0) throw new ApplicationRunTimeExeption(InfoCode.INSERT_DATA_ERROR);
        return Math.toIntExact(record.getId());
    }

    @Override
    public int updateByEntity(PrFuncs record) {
        return prFuncsDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = ApplicationRunTimeExeption.class, propagation = Propagation.REQUIRED)
    public Integer insertByBatch(List<PrFuncs> prFuncs) {
        Integer ints = funcAndRoleMapper.insertByBatch(prFuncs);
        if (ints <= 0) throw new ApplicationRunTimeExeption(InfoCode.INSERT_DATA_ERROR);
        return ints;
    }

    @Override
    public List<PrFuncs> getFuncsByAdminId(Integer adminId) {
        return funcAndRoleMapper.getPrfuncsByAdminId(adminId,1);
    }

    @Override
    @Transactional(rollbackFor = ApplicationRunTimeExeption.class)
    public Object editAuthByAdminId(Integer roleId, Long[] funcs) {
        try {
            List<PrFuncs> prFuncs = funcAndRoleMapper.selectFuncByIds(funcs,1);
            List<PrRoleFuncRelation> prfuncsByAdminId = funcAndRoleMapper.selectFuncByRoleId(roleId);
            //查询出需要更新和添加的方法ID
            List<Long> list = prFuncs.stream().mapToLong(PrFuncs::getId).boxed().collect(Collectors.toList());
            //查询目前角色拥有的权限
            List<Long> list1 = prfuncsByAdminId.stream().mapToLong(PrRoleFuncRelation::getFuncId).boxed().collect(Collectors.toList());
            ArrayList<Long> needDelIds = new ArrayList<>();
            ArrayList<Long> needInsertIds = new ArrayList<>();
            //需要添加的权限
            list.forEach(aLong -> {
                if (!list1.contains(aLong)) {
                    needInsertIds.add(aLong);
                }
            });
            //需要删除的权限
            prfuncsByAdminId.forEach(pf -> {
                if (!list.contains(pf.getFuncId())) {
                    needDelIds.add(pf.getId());
                }
            });
            if (needInsertIds.size() > 0) {
                ArrayList<PrRoleFuncRelation> rfr = new ArrayList<PrRoleFuncRelation>();
                needInsertIds.forEach(aLong -> {
                    PrRoleFuncRelation relation = new PrRoleFuncRelation();
                    relation.setFuncId(aLong);
                    relation.setRoleId(roleId);
                    rfr.add(relation);
                });
                int i = funcAndRoleMapper.insertFuncAndRoleRelationByBatch(rfr);
                if(!Integer.valueOf(rfr.size()).equals(i)){
                    throw new Exception("批量添加失败");
                }
            }
            if(needDelIds.size() > 0){
                int i = funcAndRoleMapper.deleteRelationByIds(needDelIds);
                if(!Integer.valueOf(needDelIds.size()).equals(i)){
                    throw new Exception("批量删除失败");
                }
            }
        } catch (Exception e) {
            throw new ApplicationRunTimeExeption(InfoCode.EDIT_AUTH_FAILED);
        }
        return null;
    }

    @Override
    public List<PrFuncs> getModularAuthByUser(Integer adminId) {
        return funcAndRoleMapper.getPrfuncsByAdminId(adminId,null);
    }

    @Override
    public int deleteById(Long id) {
        return prFuncsDao.deleteByPrimaryKey(id);
    }

}