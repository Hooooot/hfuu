package com.hfuu.web.service.impl;

import com.hfuu.web.dao.AdminDao;
import com.hfuu.web.entity.AdminEntity;
import com.hfuu.web.service.AbstractBaseService;
import com.hfuu.web.service.AdminService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Decription: DAO顶层接口的实现，继承Spring提供的hibernate模板
 * @CreateDate: 2019年9月25日 00点17分
 * @Author: whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 */
@Service("adminService")
    public class AdminServiceImpl extends AbstractBaseService implements AdminService {
        @Resource
        AdminDao<AdminEntity> adminDao;

        @Override
        @Transactional(rollbackFor = Exception.class)
        public Object findById(Serializable id) {
            return null;
        }

        @Override
        @Transactional(rollbackFor = Exception.class)
        public boolean isExist(Serializable id) {
            return false;
        }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List findAll() {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long count() {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List findByHql(String hql) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List findByHql(String hql, Object... param) {
        return null;
    }
}
