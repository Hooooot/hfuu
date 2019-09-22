package com.hfuu.web.dao;


import com.hfuu.web.entity.TeacherEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("teacherDAO")
public class TeacherDaoImpl implements ITeacherDao{

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public int addTeacher(TeacherEntity teacher) {
        return 0;
    }

    @Override
    public boolean isExist(TeacherEntity teacher) {
        String hql = "from TeacherEntity as t where t.tcId = ?";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setInteger(0, 1);
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
