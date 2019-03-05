package com.example.demo.service.impl;

import com.example.demo.dao.mymysql.PrFuncsMapper;
import com.example.demo.dao.mymysql.PrRoleFuncRelationMapper;
import com.example.demo.entity.mymysql.PrFuncs;
import com.example.demo.entity.mymysql.PrFuncsExample;
import com.example.demo.entity.mymysql.PrRoleFuncRelation;
import com.example.demo.entity.mymysql.PrRoleFuncRelationExample;
import com.example.demo.service.PrFuncsService;
import com.example.demo.util.ApplicationRunTimeExeption;
import com.example.demo.util.InfoCode;
import com.example.demo.vo.FuncVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrFuncsServiceImpl implements PrFuncsService {

    @Autowired
    private PrFuncsMapper prFuncsDao;

    @Autowired
    PrRoleFuncRelationMapper roleFuncRelationMapper;

    @Override
    public List<PrFuncs> selectByEntity(PrFuncsExample record) {
        List<PrFuncs> result = prFuncsDao.selectByExample(record);
        return result;
    }

    @Override
    public PrFuncs selectById(Long id) {
        PrFuncs result = prFuncsDao.selectByPrimaryKey(id);
        return result;
    }

    @Override
    @Transactional(rollbackFor = ApplicationRunTimeExeption.class, propagation = Propagation.REQUIRED)
    public int insert(PrFuncs record) {
        int insert = prFuncsDao.insert(record);
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
        Integer ints = prFuncsDao.insertByBatch(prFuncs);
        if (ints <= 0) throw new ApplicationRunTimeExeption(InfoCode.INSERT_DATA_ERROR);
        return ints;
    }

    @Override
    public List<FuncVo> getFuncsByAdminId(Integer adminId) {
        return prFuncsDao.getPrfuncsByAdminId(adminId);
    }

    @Override
    @Transactional(rollbackFor = ApplicationRunTimeExeption.class)
    public Object editAuthByAdminId(Integer roleId, Long[] funcs) {
        try {
            PrFuncsExample prFuncsExample = new PrFuncsExample();
            prFuncsExample.createCriteria().andIdIn(Arrays.asList(funcs)).andIsDelEqualTo(1);
            List<PrFuncs> prFuncs = prFuncsDao.selectByExample(prFuncsExample);
            PrRoleFuncRelationExample relationExample = new PrRoleFuncRelationExample();
            relationExample.createCriteria().andRoleIdEqualTo(roleId);
            List<PrRoleFuncRelation> prfuncsByAdminId = roleFuncRelationMapper.selectByExample(relationExample);
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
            list1.forEach(aLong -> {
                if (!list.contains(aLong)) {
                    needDelIds.add(aLong);
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
                int i = roleFuncRelationMapper.insertByBatch(rfr);
                if(!Integer.valueOf(rfr.size()).equals(i)){
                    throw new Exception("批量添加失败");
                }
            }
            if(needDelIds.size() > 0){
                PrRoleFuncRelationExample re = new PrRoleFuncRelationExample();
                re.createCriteria().andFuncIdIn(needDelIds);
                int i = roleFuncRelationMapper.deleteByExample(re);
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
    public int deleteById(Long id) {
        return prFuncsDao.deleteByPrimaryKey(id);
    }

}