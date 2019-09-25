package com.hfuu.web.service;

import com.hfuu.web.dao.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Decription: BaseService的抽象类
 * @CreateDate: 2019年9月25日 14点02分
 * @Author: whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 */
@Repository("baseService")
public abstract class AbstractBaseService<T> implements BaseService<T> {
    @Resource
    BaseDao<Object> baseDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(Object e) {
        baseDao.insert(e);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Object e) {
        baseDao.delete(e);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Object e) {
        baseDao.update(e);
    }
}
