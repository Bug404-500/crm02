package com.xxxx.crm.service.impl;

import com.xxxx.crm.dao.DemoDao;
import com.xxxx.crm.entity.Person;
import com.xxxx.crm.po.RoleMenu;
import com.xxxx.crm.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoDao demoDao;

    @Override
    public List<Person> selectList() {
        List<Person> list = demoDao.selectList();
        return list;
    }

    @Override
    public void updateUserRole(String userId, String[] roleIds) {
        //先删除该用户的角色组
        demoDao.deleteUserRole(userId);
        //再添加该用户的角色组
        /**
         * 注意看 dao 层里的接口,参数前面加了注解 @Param
         */
        demoDao.insertUserRole(userId, roleIds);
    }

    @Override
    public void updateRoleMenu(String roleId, String[] menuIds) {
        //先删除该角色的菜单组
        demoDao.deleteRoleMenu(roleId);
        //再添加该角色的菜单组
        /**
         * TODO 这里为了与上面区别，换一种写法，
         * TODO 搞一个对象list将这两个id插入对象里，mapper里使用对象list插入数据库
         */
        List<RoleMenu> lists = new ArrayList();
        for (int i = 0; i < menuIds.length; i++) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(Integer.valueOf(roleId));
            roleMenu.setMenuId(Integer.valueOf(menuIds[i]));
            lists.add(roleMenu);
        }
        lists.forEach(i -> {
            System.out.println(i.getRoleId() + "\t" + i.getMenuId());
        });

        //TODO 想像一下，假如这里发生了异常，那么是不是上面的删除已经执行，下面的新增执行不了
        //如何回滚
        //int in = 1/0;
        demoDao.insertRoleMenu(lists);
    }

}
