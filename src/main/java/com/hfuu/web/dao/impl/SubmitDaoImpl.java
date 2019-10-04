package com.hfuu.web.dao.impl;

import com.hfuu.web.dao.SubmitDao;
import com.hfuu.web.dao.base.BaseDaoImpl;
import com.hfuu.web.entity.SubmitEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Description :submitDao的实现类
 * @date :2019/9/25 18:53
 * @author :Ciel-08
 * 最后修改时间：2019/9/28
 * 最后修改人：Ciel-08
 */
@Repository("submitDao")
public class SubmitDaoImpl extends BaseDaoImpl<SubmitEntity> implements SubmitDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Long countSubmitByState(int taskId, String state) {
        Query query =this.sessionFactory.getCurrentSession().createQuery("select count(*) from SubmitEntity where taskEntity.id = ? and subState = ? ");
        query.setParameter(0, taskId);
        query.setParameter(1, state);
        return (Long) query.uniqueResult();
    }
}
