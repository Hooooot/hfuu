package com.hfuu.web.service.impl;

import com.hfuu.web.dao.BaseDao;
import com.hfuu.web.service.BaseService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;


/**
 * @author 浅忆
 */
@Service("baseService")
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    /**
     * 提供一个抽象方法，让子类返回对应的Dao
     * @return BaseDao<T>
     */
    public abstract BaseDao<T> getBaseDao();

    @Override
    public void insert(T t) {
        getBaseDao().insert(t);
    }

    @Override
    public void delete(T t) {
        getBaseDao().delete(t);
    }

    @Override
    public void update(T t) {
        getBaseDao().update(t);
    }

    @Override
    public T findById(Serializable id) {
        return getBaseDao().findById(id);
    }

    @Override
    public boolean isExist(Serializable id) {
        return getBaseDao().isExist(id);
    }

    @Override
    public List findAll() {
        return getBaseDao().findAll();
    }

    @Override
    public Long count() {
        return getBaseDao().count();
    }

    @Override
    public List findByHql(String hql) {
        return getBaseDao().findByHql(hql);
    }

    @Override
    public List findByHql(String hql, Object... param) {
        return getBaseDao().findByHql(hql, param);
    }
}
