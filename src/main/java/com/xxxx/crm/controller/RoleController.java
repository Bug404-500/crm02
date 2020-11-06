package com.xxxx.crm.controller;

import com.xxxx.crm.service.RoleService;
import com.xxxx.crm.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("index")
    public String index(){
        return "role/role";
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectList(RoleVo vo){
        Map<String,Object> map = roleService.selectList(vo);
        return map;
    }

}
