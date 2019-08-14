package com.zhongruan.etc.dao;

import com.zhongruan.etc.bean.Role;

import java.util.List;

public interface IRoleDao {

    List<Role> listRole() throws Exception;

    void insertAndGetId(Role role) throws Exception;

    void deleteById(int id) throws Exception;

    Role selectById(int id) throws Exception;

    Role selectByRoleName(String roleName) throws Exception;

    void updateById(Role role) throws Exception;

    List<Role> selectByUid(int uid) throws Exception;

}
