package com.hfuu.web.dao;


import com.hfuu.web.entity.TeacherEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("teacherDAO")
public class TeacherDaoImpl implements ITeacherDao{

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public int addTeacher(TeacherEntity teacher) {
        return 0;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
