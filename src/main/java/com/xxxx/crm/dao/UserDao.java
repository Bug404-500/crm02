package com.xxxx.crm.dao;

import com.xxxx.crm.po.User;
import com.xxxx.crm.vo.UserVo;

import java.util.List;

public interface UserDao {
    User selectUserByUserName(String userName);

    User selectUserInfoById(String id);

    void updateUserPwd(String id, String userPwd);

    List<User> selectList(UserVo vo);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(List<String> ids);
}
