package com.hfuu.web.service.impl;

import com.hfuu.web.dao.DepDao;
import com.hfuu.web.entity.DepartmentEntity;
import com.hfuu.web.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：机构Service的实现类
 * @author Ciel-08
 * 创建时间：2019/09/24 10.25
 * 最后修改时间：
 * 最后修改人：
 */

@Repository("depService")
public class DepServiceImpl implements DepService {

    @Autowired
    private DepDao depDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(DepartmentEntity d) {
        depDao.insert(d);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(DepartmentEntity d) {
        depDao.delete(d);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DepartmentEntity d) {
        depDao.update(d);
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
    public List<DepartmentEntity> findAll() {
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
}
