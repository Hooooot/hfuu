package com.hfuu.web.dao.impl;


import com.hfuu.web.dao.ITeacherDao;
import com.hfuu.web.entity.TeacherEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("teacherDAO") // byName注入
public class TeacherDaoImpl extends BaseDaoImpl implements ITeacherDao {
    @Override
    public void updateById(Object entity) {
        TeacherEntity teacher = (TeacherEntity) entity;
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update TeacherEntity e set e.tcName=:tcName,e.tcNum=:tcNum,e.tcPw=:tcPw,e.depNum=:depNum," +
                "e.tcSex=:tcSex,e.tcPhone=:tcPhone,e.tcAvatar=:tcAvatar where e.tcId=:tcId";
        Query query = session.createQuery(hql);
        query.setProperties(teacher);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "delete from TeacherEntity e where e.tcId=?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, id);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public List select(Object entity) {
        TeacherEntity teacher = (TeacherEntity)entity;
        Session session = sessionFactory.getCurrentSession();
        String hql = null;
        if (teacher.getTcId() != 0){
            hql = "from TeacherEntity e where e.tcId = :tcId";
        }else if (teacher.getTcNum() != null){
            hql = "from TeacherEntity e where e.tcNum = :tcNum";
        }else if (teacher.getTcPw() != null && teacher.getTcName() != null){
            hql = "from TeacherEntity e where e.tcName = :tcName and e.tcPw = :tcPw";
        }
        Query query = session.createQuery(hql);
        query.setProperties(teacher);
        query.setMaxResults(1);
        return query.list();
    }

    @Override
    public List getAll() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from TeacherEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        transaction.commit();
        return list;
    }

    @Override
    public boolean isExist(Object entity) {
        return this.select(entity).size() > 0;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
