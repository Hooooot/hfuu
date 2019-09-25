package com.hfuu.web.service.impl;

import com.hfuu.web.dao.BaseDao;
import com.hfuu.web.dao.DepDao;
import com.hfuu.web.entity.Department;
import com.hfuu.web.service.DepService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Decription: 机构Service的实现类
 * @CreateDate: 2019-09-24 10:25
 * @Author: Ciel-08
 * 最后修改时间：
 * 最后修改人：
 */
@Service("depService")
@Transactional
public class DepServiceImpl extends BaseServiceImpl<Department> implements DepService {

    @Resource
    private DepDao depDao;

    @Override
    public BaseDao<Department> getBaseDao() {
        return depDao;
    }
}
