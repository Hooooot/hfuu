package com.hfuu.web.service.base;

import com.hfuu.web.dao.base.BaseDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @Decription :
 * @CreateDate :
 * @author : 浅忆
 * 最后修改时间：
 * 最后修改人：
 */
@Service("baseService")
@Transactional(rollbackFor = Exception.class)
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
    public List pageQuery(int first, int max) {
        return getBaseDao().pageQuery(first, max);
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
