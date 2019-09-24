package com.hfuu.web.service.impl;

import com.hfuu.web.dao.DepDao;
import com.hfuu.web.entity.DepartmentEntity;
import com.hfuu.web.service.DepService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Decription: 机构Service的实现类
 * @CreateDate: 2019-09-24 10:25
 * @Author: Ciel-08
 * 最后修改时间：
 * 最后修改人：
 */
@Repository("depService")
public class DepServiceImpl implements DepService {

    @Resource
    private DepDao depDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(Object d) {
        depDao.insert((DepartmentEntity) d);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Object d) {
        depDao.delete((DepartmentEntity)d);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Object d) {
        depDao.update((DepartmentEntity)d);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DepartmentEntity findById(Serializable id) {
        return depDao.findById(DepartmentEntity.class, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isExist(Serializable id) {
        return depDao.isExist(DepartmentEntity.class, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List findAll() {
        return depDao.findAll(DepartmentEntity.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long count() {
        return depDao.count(DepartmentEntity.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List findByHql(String hql) {
        return depDao.findByHql(hql);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List findByHql(String hql, Object... param) {
        return depDao.findByHql(hql, param);
    }
}
