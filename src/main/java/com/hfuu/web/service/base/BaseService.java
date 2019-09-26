package com.hfuu.web.service.base;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @Decription :
 * @CreateDate :
 * @author : 浅忆
 * 最后修改时间：
 * 最后修改人：
 */
@Service
public interface BaseService<T> {
    void insert(T e);
    void delete(T e);
    void update(T e);
    T findById(Serializable id);
    boolean isExist(Serializable id);
    List findAll();
    Long count();
    List findByHql(String hql);
    List findByHql(String hql, Object... param);
}
