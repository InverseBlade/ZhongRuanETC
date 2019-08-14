package com.zhongruan.etc.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhongruan.etc.bean.Role;
import com.zhongruan.etc.bean.UserInfo;
import com.zhongruan.etc.dao.IRoleDao;
import com.zhongruan.etc.dao.IUserInfoDao;
import com.zhongruan.etc.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private IUserInfoDao userInfoDao;

    @Autowired
    private IRoleDao roleDao;

    private Collection<? extends GrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            UserInfo userInfo = userInfoDao.selectByUserName(username);
            List<Role> roles = roleDao.selectByUid(userInfo.getId());
            user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), getAuthority(roles));
        } catch (UsernameNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean login(UserInfo userInfo) {
        boolean flag = false;
        try {
            UserInfo info = userInfoDao.selectByUserName(userInfo.getUsername());
            if (info.getPassword().equals(userInfo.getPassword())) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public UserInfo findByUserName(String username) {
        UserInfo userInfo = null;
        try {
            userInfo = userInfoDao.selectByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    @Override
    public List<UserInfo> findAllUser(String keyword, int page, int size) {
        List<UserInfo> users = new ArrayList<>();
        try {
            PageHelper.startPage(page, size);
            users = userInfoDao.listAllUser(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public int save(UserInfo userInfo) {
        int uid = 0;
        try {
            userInfoDao.insertAndGetId(userInfo);
            uid = userInfo.getId();
            System.out.println(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uid;
    }

    @Override
    public void removeById(Integer id) {
        try {
            userInfoDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void batchDelete(int[] ids) {
        List<Integer> list = new ArrayList<>();
        for (int i : ids) {
            list.add(i);
        }
        userInfoDao.deleteAllByIds(list);
    }

    @Override
    public void modify(UserInfo userInfo) {
        try {
            userInfoDao.updateById(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserInfo findById(Integer id) {
        try {
            return userInfoDao.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
