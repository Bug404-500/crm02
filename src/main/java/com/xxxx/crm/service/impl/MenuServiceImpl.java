package com.xxxx.crm.service.impl;

import com.xxxx.crm.dao.MenuDao;
import com.xxxx.crm.po.Menu;
import com.xxxx.crm.service.MenuService;
import com.xxxx.crm.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;


    @Override
    public List<Menu> selectList() {
        List<Menu> list = menuDao.selectList();
        return list;
    }

    @Override
    public void insert(MenuVo vo) {

        menuDao.insert(vo);
    }

    @Override
    public void update(MenuVo vo) {
        menuDao.update(vo);
    }
}
