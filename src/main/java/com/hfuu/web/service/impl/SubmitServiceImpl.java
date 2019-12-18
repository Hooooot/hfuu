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
 * @author : Ciel-08
 * 最后修改时间：
 * 最后修改人：
 * @Description :
 * @date : 2019/9/25 19:03
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

    @Override
    public Long countSubmitByState(int taskId, String state) {
        return submitDao.countSubmitByState(taskId, state);
    }

    @Override
    public int updateScoreById(int id, short score) {
        return submitDao.executeHql("update SubmitEntity s set s.score=? where s.subId=?", score, id);
    }

    @Override
    public int updateScoreAndStateById(int id, short score, String state) {
        return submitDao.executeHql("update SubmitEntity s set s.score=?, s.subState=? where s.subId=?", score, state, id);
    }
}
