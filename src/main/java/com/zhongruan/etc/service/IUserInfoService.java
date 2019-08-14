package com.zhongruan.etc.service;

import com.zhongruan.etc.bean.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserInfoService extends UserDetailsService {

    boolean login(UserInfo userInfo);

    UserInfo findByUserName(String username);

    UserInfo findById(Integer id);

    List<UserInfo> findAllUser(String keyword, int page, int size);

    int save(UserInfo userInfo);

    void removeById(Integer id);

    void batchDelete(int[] ids);

    void modify(UserInfo userInfo);

}
