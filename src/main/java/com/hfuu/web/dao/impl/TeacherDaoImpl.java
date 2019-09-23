package com.hfuu.web.dao.impl;


import com.hfuu.web.dao.ITeacherDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository("teacherDAO")
public class TeacherDaoImpl implements ITeacherDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(Object entity) {
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Object entity) {
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Object entity) {
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List select(Object entity) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List getAll() {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isExist(Object entity) {
        String hql = "from TeacherEntity as t where t.tcId = ?1";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setParameter(1, 1);
        List list = query.list();
        return list.size() > 0;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
