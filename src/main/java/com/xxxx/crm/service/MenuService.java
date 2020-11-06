package com.xxxx.crm.service;


import com.xxxx.crm.po.Menu;
import com.xxxx.crm.vo.MenuVo;

import java.util.List;

public interface MenuService {

    List<Menu> selectList();

    void insert(MenuVo vo);

    void update(MenuVo vo);
}
