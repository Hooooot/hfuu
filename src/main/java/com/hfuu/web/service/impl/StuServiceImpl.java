package com.hfuu.web.service.impl;

import com.hfuu.web.dao.StuDao;
import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.StudentEntity;
import com.hfuu.web.service.StuService;
import com.hfuu.web.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Decription :
 * @CreateDate : 2019/9/25 19:03
 * @author : Ciel-08
 * 最后修改时间：
 * 最后修改人：
 */
@Service("stuService")
@Transactional(rollbackFor = Exception.class)
public class StuServiceImpl extends BaseServiceImpl<StudentEntity> implements StuService {

    @Resource
    private StuDao stuDao;

    @Override
    public BaseDao<StudentEntity> getBaseDao() {
        return stuDao;
    }
}
