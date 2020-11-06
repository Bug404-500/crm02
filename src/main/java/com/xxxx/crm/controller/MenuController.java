package com.xxxx.crm.controller;

import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.po.Menu;
import com.xxxx.crm.service.MenuService;
import com.xxxx.crm.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;


    @RequestMapping("list")
    public ResultInfo selectList(){
        List<Menu> list = menuService.selectList();

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(200);
        resultInfo.setMsg("菜单");
        resultInfo.setResult(list);
        return resultInfo;
    }


    @RequestMapping("insert")
    public ResultInfo insert(MenuVo vo){
        menuService.insert(vo);
        ResultInfo resultInfo = new ResultInfo();
        return resultInfo;
    }

    @RequestMapping("update")
    public ResultInfo update(MenuVo vo){
        menuService.update(vo);
        ResultInfo resultInfo = new ResultInfo();
        return resultInfo;
    }

}
