package com.hfuu.web.dao.impl;

import com.hfuu.web.dao.CourseDao;
import com.hfuu.web.dao.base.BaseDaoImpl;
import com.hfuu.web.entity.ClassEntity;
import com.hfuu.web.entity.CourseEntity;
import com.hfuu.web.entity.TeacherEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :Ciel-08
 * 最后修改时间：2019年10月27日 23点04分
 * 最后修改人：whh0987@foxmail.com
 * @Description :
 * @date :2019/9/25 18:53
 */
@Repository("courseDao")
public class CourseDaoImpl extends BaseDaoImpl<CourseEntity> implements CourseDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<CourseEntity> getCourseByClassNumAndTcNumAndTerm(List<String> classNum, String tcNum, String term) {
        if (classNum == null) {
            return null;
        }
        StringBuilder hql = new StringBuilder("from CourseEntity c where c.tcEntity=? and c.term=? and (");
        for (String s : classNum) {
            hql.append("c.classEntity=? or ");
        }
        String hqls = hql.substring(0, hql.length() - 3) + ")";
        Query q = sessionFactory.getCurrentSession().createQuery(hqls);
        TeacherEntity t = new TeacherEntity();
        t.setTcNum(tcNum);
        q.setParameter(0, t);
        q.setParameter(1, term);
        for (int i = 0; i < classNum.size(); i++) {
            ClassEntity c = new ClassEntity();
            c.setClassNum(classNum.get(i));
            q.setParameter(i + 2, c);
        }
        return q.list();
    }
}
