package com.zhongruan.etc.service;

import com.zhongruan.etc.bean.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll(int page, int size);

    Role findById(int id);

    Role findByName(String roleName);

    void saveRole(Role role);

    void modify(Role role);

    void removeById(int id);

}
