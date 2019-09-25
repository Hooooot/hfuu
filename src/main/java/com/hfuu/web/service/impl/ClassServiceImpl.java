package com.hfuu.web.service.impl;

import com.hfuu.web.dao.ClassDao;
import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.ClassEntity;
import com.hfuu.web.service.ClassService;
import com.hfuu.web.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author: Ciel-08
 * 创建时间：2019/9/25 19:09
 * 最后修改时间：
 * 最后修改人：
 */
@Service("classService")
@Transactional(rollbackFor = Exception.class)
public class ClassServiceImpl extends BaseServiceImpl<ClassEntity> implements ClassService {

    @Resource
    private ClassDao classDao;

    @Override
    public BaseDao<ClassEntity> getBaseDao() {
        return classDao;
    }
}
