package com.hfuu.web.service.impl;

import com.hfuu.web.dao.SubmitDao;
import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.SubmitEntity;
import com.hfuu.web.service.SubmitService;
import com.hfuu.web.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author: Ciel-08
 * 创建时间：2019/9/25 19:16
 * 最后修改时间：
 * 最后修改人：
 */
@Service("submitService")
@Transactional(rollbackFor = Exception.class)
public class SubmitServiceImpl extends BaseServiceImpl<SubmitEntity> implements SubmitService {

    @Resource
    private SubmitDao submitDao;

    @Override
    public BaseDao<SubmitEntity> getBaseDao() {
        return submitDao;
    }
}
