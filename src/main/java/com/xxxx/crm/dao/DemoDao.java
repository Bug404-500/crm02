package com.xxxx.crm.dao;

import com.xxxx.crm.entity.Person;
import com.xxxx.crm.po.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DemoDao {
    List<Person> selectList();

    void deleteUserRole(String userId);

    void insertUserRole(@Param("userId") String userId, @Param("array") String[] roleIds);

    void deleteRoleMenu(String roleId);

    void insertRoleMenu(@Param("list") List<RoleMenu> list);
}
