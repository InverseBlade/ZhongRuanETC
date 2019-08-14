package com.zhongruan.etc.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhongruan.etc.bean.Role;
import com.zhongruan.etc.dao.IRoleDao;
import com.zhongruan.etc.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll(int page, int size) {
        List<Role> roles = null;
        try {
            PageHelper.startPage(page, size);
            roles = roleDao.listRole();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public Role findById(int id) {
        Role role = null;
        try {
            role = roleDao.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public Role findByName(String roleName) {
        Role role = null;
        try {
            role = roleDao.selectByRoleName(roleName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public void saveRole(Role role) {
        try {
            roleDao.insertAndGetId(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(Role role) {
        try {
            roleDao.updateById(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(int id) {
        try {
            roleDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
