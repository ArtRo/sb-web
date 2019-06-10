package com.example.demo.dao.custom;

import com.example.demo.entity.mymysql.PrFuncs;
import com.example.demo.entity.mymysql.PrRole;
import com.example.demo.entity.mymysql.PrRoleFuncRelation;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhf_ndm on 2019/5/10
 * the previous bug derived from the previous
 */
public interface FuncAndRoleMapper {
    Integer insertByBatch(List<PrFuncs> prFuncs);

    List<PrFuncs> getPrfuncsByAdminId(@Param("adminId") Integer adminId,@Param("parentId") Integer parentId);

    int insertFuncAndRoleRelationByBatch(ArrayList<PrRoleFuncRelation> rfr);


    List<PrFuncs> selectFuncByIds(@Param("funcs") Long[] funcs, @Param("isDel") Integer isDel);

    List<PrRoleFuncRelation> selectFuncByRoleId(@Param("roleId") Integer roleId);

    int deleteRelationByIds(@Param("needDelIds") List<Long> needDelIds);

    List<PrRole> selectByState(@Param("isDel") Integer isDel);
}
