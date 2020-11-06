package com.xxxx.crm.service;

import com.xxxx.crm.po.SaleChance;
import com.xxxx.crm.vo.SaleChanceVo;

import java.util.Map;

public interface SaleChanceService {

    Map<String,Object> selectList(SaleChanceVo vo);

    void insert(SaleChance saleChance);

    SaleChance selectSaleChanceInfoById(String saleChanceId);

    void update(SaleChance saleChance);

    void delete(String ids);

    void deleteIds(String[] ids);
}
