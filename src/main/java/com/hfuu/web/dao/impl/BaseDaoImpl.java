package com.hfuu.web.dao.impl;

import com.hfuu.web.dao.BaseDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Decription: DAO顶层接口的实现，继承Spring提供的hibernate模板
 * @CreateDate:
 * @Author: 浅忆
 * 最后修改时间：
 * 最后修改人：
 */
@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void insert(T t) {
        sessionFactory.getCurrentSession().save(t);
    }

    @Override
    public void delete(T t) {
        sessionFactory.getCurrentSession().delete(t);
    }

    @Override
    public void update(T t) {
        sessionFactory.getCurrentSession().update(t);
    }

    @Override
    public T findById(Class<T> c, Serializable id) {
            return sessionFactory.getCurrentSession().get(c, id);
    }

    @Override
    public boolean isExist(Class<T> c, Serializable id) {
        return findById(c, id)!=null;
    }

    @Override
    public List findAll(Class<T> c) {
        return sessionFactory.getCurrentSession().createQuery("from "+c.getSimpleName()).list();
    }

    @Override
    public Long count(Class<T> c) {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(*) from " + c.getSimpleName());
        return (Long) query.list().get(0);
    }

    @Override
    public List findByHql(String hql) {
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List findByHql(String hql, Object... param) {
        Query q = sessionFactory.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.list();
    }
}
