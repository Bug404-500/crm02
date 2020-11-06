package com.xxxx.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.dao.SaleChanceDao;
import com.xxxx.crm.po.SaleChance;
import com.xxxx.crm.service.SaleChanceService;
import com.xxxx.crm.vo.SaleChanceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleChanceServiceImpl implements SaleChanceService {
    @Autowired
    private SaleChanceDao saleChanceDao;

    @Override
    public Map<String, Object> selectList(SaleChanceVo vo) {
        //使用 pageHelper 帮我们处理了总记录数
        PageHelper.startPage(vo.getPage(), vo.getLimit());
        //
        List<SaleChance> list = saleChanceDao.selectList(vo);
        PageInfo<SaleChance> pageInfo = new PageInfo<>(list);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", list);
        return map;
    }

    @Override
    public void insert(SaleChance saleChance) {
        saleChance.setCreateDate(new Date());
        saleChance.setCreateMan("田自成");
        saleChanceDao.insert(saleChance);
    }

    @Override
    public SaleChance selectSaleChanceInfoById(String saleChanceId) {
        SaleChance saleChance = saleChanceDao.selectSaleChanceInfoById(saleChanceId);
        return saleChance;
    }

    @Override
    public void update(SaleChance saleChance) {
        Integer i = saleChanceDao.update(saleChance);
    }

    @Override
    public void delete(String ids) {
        saleChanceDao.delete(ids);
    }

    @Override
    public void deleteIds(String[] ids) {
        saleChanceDao.deleteIds(ids);
    }
}
