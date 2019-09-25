package com.hfuu.web.service.impl;

import com.hfuu.web.dao.TeacherDao;
import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.service.TeacherService;
import com.hfuu.web.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author: Ciel-08
 * 创建时间：2019/9/25 19:18
 * 最后修改时间：
 * 最后修改人：
 */
@Service("teacherService")
@Transactional(rollbackFor = Exception.class)
public class TeacherServiceImpl extends BaseServiceImpl<TeacherEntity> implements TeacherService {

    @Resource
    private TeacherDao teacherDao;

    @Override
    public BaseDao<TeacherEntity> getBaseDao() {
        return teacherDao;
    }
}
