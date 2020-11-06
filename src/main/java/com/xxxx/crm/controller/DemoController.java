package com.xxxx.crm.controller;

import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.entity.Person;
import com.xxxx.crm.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("list")
    public ResultInfo selectList(){
        List<Person> list = demoService.selectList();
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(200);
        resultInfo.setResult(list);
        return resultInfo;
    }

    @RequestMapping("delete")
    public ResultInfo delete(){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(200);
        return resultInfo;
    }


    /**
     * 更新  用户-角色  表
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping("updateUserRole")
    public ResultInfo updateUserRole(String userId,String[] roleIds){
        demoService.updateUserRole(userId,roleIds);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(200);
        return resultInfo;
    }

    /**
     * 更新  角色-菜单 表
     * @param roleId
     * @param menuIds
     * @return
     */
    @RequestMapping("updateRoleMenu")
    public ResultInfo updateRoleMenu(String roleId,String[] menuIds){
        demoService.updateRoleMenu(roleId,menuIds);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(200);
        return resultInfo;
    }
}
