package com.zhongruan.etc.dao;

import com.zhongruan.etc.bean.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserRoleDao {

    void insert(@Param("uid") Integer uid, @Param("rid") Integer rid) throws Exception;

    void deleteByUidAndRid(Integer uid, Integer rid) throws Exception;

    void deleteByUid(Integer uid) throws Exception;

    UserRole selectByUidAndRid(Integer uid, Integer rid) throws Exception;

    List<UserRole> selectByUid(@Param("uid") Integer uid) throws Exception;

    List<UserRole> selectByRid(Integer rid) throws Exception;

}
