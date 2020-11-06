package com.xxxx.crm.service;

import com.xxxx.crm.entity.Person;

import java.util.List;

public interface DemoService{
    List<Person> selectList();

    void updateRoleMenu(String roleId, String[] menuIds);

    void updateUserRole(String userId, String[] roleIds);
}
