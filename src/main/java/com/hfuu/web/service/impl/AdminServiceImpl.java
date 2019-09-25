package com.hfuu.web.service.impl;

import com.hfuu.web.dao.AdminDao;
import com.hfuu.web.dao.BaseDao;
import com.hfuu.web.entity.Admin;
import com.hfuu.web.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author 浅忆
 */
@Service("adminService")
@Transactional
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Override
    public BaseDao<Admin> getBaseDao() {
        return adminDao;
    }
}
