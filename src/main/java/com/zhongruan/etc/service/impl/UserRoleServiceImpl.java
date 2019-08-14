package com.zhongruan.etc.service.impl;

import com.zhongruan.etc.bean.UserRole;
import com.zhongruan.etc.dao.IUserRoleDao;
import com.zhongruan.etc.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    private IUserRoleDao userRoleDao;

    @Override
    public void saveUserRoles(int uid, int[] rid) {
        try {
            userRoleDao.deleteByUid(uid);
            for (int i = 0; i < rid.length; i++) {
                try {
                    userRoleDao.insert(uid, rid[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRolesByUid(int uid) {

    }

    @Override
    public UserRole findByUidAndRid(int uid, int rid) {
        return null;
    }

    @Override
    public List<UserRole> findByUid(int uid) {
        List<UserRole> userRoles = new ArrayList<>();
        try {
            userRoles = userRoleDao.selectByUid(uid);
            if (userRoles == null) {
                userRoles = new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRoles;
    }
}
