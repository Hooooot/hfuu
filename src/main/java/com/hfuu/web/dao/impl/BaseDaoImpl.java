package com.hfuu.web.dao.impl;

import com.hfuu.web.dao.IBaseDao;
import com.hfuu.web.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository("baseDAO")
public abstract class BaseDaoImpl implements IBaseDao {
    @Resource
    SessionFactory sessionFactory;

    @Override
    public void insert(Object entity) {
        String className = entity.getClass().getSimpleName();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        switch (className) {
            case "TeacherEntity":
                session.save(entity);
                break;
            case "StudentEntity":
                session.save(entity);
                break;
            case "AdminEntity":
                session.save(entity);
                break;
            case "ClazzEntity":
                session.save(entity);
                break;
            case "CourseEntity":
                session.save(entity);
                break;
            case "DepartmentEntity":
                session.save(entity);
                break;
            case "SubmitEntity":
                session.save(entity);
                break;
            case "TaskEntity":
                session.save(entity);
                break;
        }
        transaction.commit();
        session.close();
    }

    private List studentSelect(StudentEntity entity) {
        String hql = "";
        Query query = null;
        Session session = sessionFactory.openSession();
        if (entity.getStuId() != 0){
            hql = "from StudentEntity as t where t.stuId = ?1";
            query = session.createQuery(hql);
            query.setParameter(1, entity.getStuId());
        }else if (entity.getStuNum() != null){
            hql = "from StudentEntity as t where t.stuNum = ?1";
            query = session.createQuery(hql);
            query.setParameter(1, entity.getStuNum());

        }else if (entity.getStuNum()!=null && entity.getStuPw()!=null){
            hql = "from StudentEntity as t where t.stuName = ?1 and t.stuPw = ?2";
            query = session.createQuery(hql);
            query.setParameter(1, entity.getStuName());
            query.setParameter(2, entity.getStuPw());
        }
        if (query == null){
            return null;
        }
        query.setMaxResults(1);
        return query.list();
    }

    private List teacherSelect(TeacherEntity entity) {
        String hql = "";
        Query query = null;
        Session session = sessionFactory.openSession();
        if (entity.getTcId() != 0){
            hql = "from TeacherEntity as t where t.tcId = ?1";
            query = session.createQuery(hql);
            query.setParameter(1, entity.getTcId());
        }else if (entity.getTcNum() != null){
            hql = "from TeacherEntity as t where t.tcNum = ?1";
            query = session.createQuery(hql);
            query.setParameter(1, entity.getTcNum());

        }else if (entity.getTcName()!=null && entity.getTcPw()!=null){
            hql = "from TeacherEntity as t where t.tcName = ?1 and t.tcPw = ?2";
            query = session.createQuery(hql);
            query.setParameter(1, entity.getTcName());
            query.setParameter(2, entity.getTcPw());
        }
        if (query == null){
            return null;
        }
        query.setMaxResults(1);
        return query.list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
