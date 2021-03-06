package com.hfuu.web.service.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author : 浅忆
 * 最后修改时间：
 * 最后修改人：
 * @Description :
 * @date :
 */
public interface BaseService<T> {
    void insert(T e);

    void delete(T e);

    void update(T e);

    T findById(Serializable id);

    boolean isExist(Serializable id);

    List findAll();

    List pageQuery(int currPage, int max);

    Long count();

    List findByHql(String hql);

    List findByHql(String hql, Object... param);
}
