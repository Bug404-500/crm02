package com.xxxx.crm.dao;

import com.xxxx.crm.po.Menu;
import com.xxxx.crm.vo.MenuVo;

import java.util.List;

public interface MenuDao {
    List<Menu> selectList();

    void insert(MenuVo vo);

    void update(MenuVo vo);
}
