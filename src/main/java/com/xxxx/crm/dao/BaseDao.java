package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseQuery;

import java.util.List;

public interface BaseDao<T,E> {
    Integer insertEntity(T entity);

    Integer deleteById(E id);

    Integer updateEntity(E id);

    <T> List<T> selectListByParams(BaseQuery baseQuery);

    <T> T selectTByParams(T entity);
}
