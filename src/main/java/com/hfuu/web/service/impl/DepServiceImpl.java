package com.hfuu.web.service.impl;

import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.dao.DepDao;
import com.hfuu.web.entity.DepEntity;
import com.hfuu.web.service.DepService;
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
@Service("depService")
@Transactional(rollbackFor = Exception.class)
public class DepServiceImpl extends BaseServiceImpl<DepEntity> implements DepService {

    @Resource
    private DepDao depDao;

    @Override
    public BaseDao<DepEntity> getBaseDao() {
        return depDao;
    }
}
