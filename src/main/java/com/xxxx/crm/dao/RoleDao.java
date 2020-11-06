package com.xxxx.crm.dao;

import com.xxxx.crm.po.Role;
import com.xxxx.crm.vo.RoleVo;

import java.util.List;

public interface RoleDao {
    List<Role> selectList(RoleVo vo);
}
