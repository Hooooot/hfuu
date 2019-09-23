package com.hfuu.web.service.teacher.impl;

import com.hfuu.web.dao.impl.BaseDaoImpl;
import com.hfuu.web.service.teacher.IBaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("baseService")
public abstract class BaseServiceImpl implements IBaseService {
    @Resource
    private BaseDaoImpl baseDao;

    @Override
    public void save(Object entity) {
        baseDao.insert(entity);
    }
}
