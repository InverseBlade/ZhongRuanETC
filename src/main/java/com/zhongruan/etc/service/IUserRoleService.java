package com.zhongruan.etc.service;

import com.zhongruan.etc.bean.UserRole;

import java.util.List;

public interface IUserRoleService {

    void saveUserRoles(int uid, int[] rid);

    void deleteRolesByUid(int uid);

    UserRole findByUidAndRid(int uid, int rid);

    List<UserRole> findByUid(int uid);

}
