package com.xxxx.crm.vo;

import com.xxxx.crm.base.BaseQuery;
import lombok.Data;

@Data
public class UserVo extends BaseQuery {
    private String userName;
    private String email;
    private String phone;

}
