package com.hfuu.web.dao.base;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @Decription :基础Dao的实现类
 * @CreateDate :2019/9/25 18:53
 * @author :Ciel-08
 * 最后修改时间：
 * 最后修改人：
 */
@Repository("baseDao")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    private Class<T> clazz;

    public BaseDaoImpl(){
        Class<?> c = this.getClass();
        Type t = c.getGenericSuperclass();
        if (t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.clazz = (Class<T>) p[0];
        }
        else {
            System.err.println("父类类型与ParameterizedType不匹配，无法强转！");
        }
    }

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
    public T findById(Serializable id) {
            return sessionFactory.getCurrentSession().get(clazz, id);
    }

    @Override
    public boolean isExist(Serializable id) {
        return findById(id)!=null;
    }

    @Override
    public List findAll() {
        return sessionFactory.getCurrentSession().createQuery("from "+clazz.getSimpleName()).list();
    }

    @Override
    public List pageQuery(int currPage, int max) {
        Query query = sessionFactory.getCurrentSession().createQuery("from "+clazz.getSimpleName());
        int first = (currPage - 1) * max;
        query.setFirstResult(first);
        query.setMaxResults(max);
        return query.list();
    }

    @Override
    public Long count() {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(*) from " + clazz.getSimpleName());
        return (Long) query.uniqueResult();
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
