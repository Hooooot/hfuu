package com.hfuu.web.service;

import com.hfuu.web.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Decription:
 * @CreateDate:
 * @Author:
 * 最后修改时间：
 * 最后修改人：
 */
@Repository("baseService")
public abstract class AbstractBaseService<T> implements BaseService<T> {
    @Resource
    BaseDao<Object> baseDao;

    @Override
    public void insert(Object e) {
        baseDao.insert(e);
    }

    @Override
    public void delete(Object e) {
        baseDao.delete(e);
    }

    @Override
    public void update(Object e) {
        baseDao.update(e);
    }
}
