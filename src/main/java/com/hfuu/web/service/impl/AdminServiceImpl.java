package com.hfuu.web.service.impl;

import com.hfuu.web.dao.AdminDao;
import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.AdminEntity;
import com.hfuu.web.service.AdminService;
import com.hfuu.web.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author : Ciel-08
 * 最后修改时间：
 * 最后修改人：
 * @Description :
 * @date : 2019/9/25 19:03
 */
@Service("adminService")
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl extends BaseServiceImpl<AdminEntity> implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Override
    public BaseDao<AdminEntity> getBaseDao() {
        return adminDao;
    }
}
