package com.hfuu.web.dao.impl;

import com.hfuu.web.dao.BaseDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * DAO顶层接口的实现，继承Spring提供的hibernate模板
 * @author 浅忆
 */
@Repository("baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void insert(T t) {
        this.getHibernateTemplate().save(t);
    }

    @Override
    public void delete(T t) {
        this.getHibernateTemplate().delete(t);
    }

    @Override
    public void update(T t) {
        this.getHibernateTemplate().update(t);
    }

    @Override
    public T findById(Class<T> c, Serializable id) {
        return (T)this.getHibernateTemplate().get(c, id);
    }

    @Override
    public boolean isExist(Class<T> c, Serializable id) {
        return findById(c, id)!=null;
    }

    @Override
    public List<T> findAll(Class<T> c) {

        return (List<T>) this.getHibernateTemplate().find("from "+c.getSimpleName());
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
