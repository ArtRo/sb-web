package com.example.demo.dao.custom;

import com.example.demo.entity.mymysql.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dhf_ndm on 2019/5/10
 * the previous bug derived from the previous
 */

@Repository
public interface AdminMapper {
    List<AdminUser> selectByUserName(@Param("username") String username);
}
