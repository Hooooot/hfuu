package com.hfuu.web.service.impl;

import com.hfuu.web.dao.CourseDao;
import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.CourseEntity;
import com.hfuu.web.service.CourseService;
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
@Service("courseService")
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl extends BaseServiceImpl<CourseEntity> implements CourseService {

    @Resource
    private CourseDao courseDao;

    @Override
    public BaseDao<CourseEntity> getBaseDao() {
        return courseDao;
    }
}
