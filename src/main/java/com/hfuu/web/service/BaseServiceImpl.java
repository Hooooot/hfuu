package com.hfuu.web.service;

import com.hfuu.web.dao.impl.BaseDaoImpl;
import com.hfuu.web.service.IBaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("baseService")
public abstract class BaseServiceImpl implements IBaseService {
    @Resource
    private BaseDaoImpl baseDao;

    @Override
    public void save(Object entity) {
        baseDao.insert(entity);
    }
}
