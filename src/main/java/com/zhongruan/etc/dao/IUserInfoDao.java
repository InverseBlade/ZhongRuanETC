package com.zhongruan.etc.dao;

import com.zhongruan.etc.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserInfoDao {

    UserInfo selectById(Integer id) throws Exception;

    UserInfo selectByUserName(@Param("username") String username) throws Exception;

    List<UserInfo> listAllUser(@Param("keyword") String keyword) throws Exception;

    void insertAndGetId(UserInfo userInfo) throws Exception;

    void deleteById(Integer id) throws Exception;

    void updateById(UserInfo userInfo) throws Exception;

    void deleteAllByIds(List<Integer> ids);

}
