package com.xxxx.crm.service;

import com.xxxx.crm.vo.RoleVo;

import java.util.Map;

public interface RoleService {
    Map<String, Object> selectList(RoleVo vo);
}
