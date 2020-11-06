package com.xxxx.crm.dao;

import com.xxxx.crm.po.SaleChance;
import com.xxxx.crm.vo.SaleChanceVo;

import java.util.List;

public interface SaleChanceDao {

    List<SaleChance> selectList(SaleChanceVo vo);

    void insert(SaleChance saleChance);

    SaleChance selectSaleChanceInfoById(String saleChanceId);

    Integer update(SaleChance saleChance);

    void delete(String ids);

    void deleteIds(String[] array);
}
