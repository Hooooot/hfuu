package com.hfuu.web.dao.impl;

import com.hfuu.web.dao.TeacherDao;
import com.hfuu.web.dao.base.BaseDaoImpl;
import com.hfuu.web.entity.*;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @Description :
 * @date :2019/9/25 19:01
 * @author :Ciel-08
 * 最后修改时间：2019年10月8日 17点05分
 * 最后修改人：whh0987@foxmail.com
 */
@Repository("teacherDao")
public class TeacherDaoImpl extends BaseDaoImpl<TeacherEntity> implements TeacherDao {
    @Override
    public TeacherEntity getTeacherByNameAndPw(String name, String pw) {
        TeacherEntity tc = new TeacherEntity();
        tc.setTcName(name);
        tc.setTcPw(pw);
        List list = this.findByHql("from TeacherEntity tc where tc.tcName=? and tc.tcPw=?", name, pw);
        if (list.size() != 0){
            return (TeacherEntity) list.get(0);
        }
        return null;
    }

    @Override
    public List<StudentEntity> getStudentsByTcNumAndTermAndClazzNum(String tcNum, String term, String clazzNum){
        //noinspection unchecked
        return (List<StudentEntity>) this.findByHql("select s from StudentEntity s inner join CourseEntity c " +
                "on c.classEntity=s.classEntity where c.tcEntity.tcNum=? and c.term=? and s.classEntity.classNum=?", tcNum, term, clazzNum);
    }

    @Override
    public List<StudentEntity> getStudentsByTcNumAndTermAndClazzNumAndTaskid(String tcNum, String term, String clazzNum, int taskId) {
        //noinspection unchecked
        return (List<StudentEntity>) this.findByHql("select s from StudentEntity s inner join CourseEntity c " +
                "on c.classEntity=s.classEntity inner join TaskEntity t on t.cozEntity=c " +
                "where c.tcEntity.tcNum=? and c.term=? and s.classEntity.classNum=?" +
                " and t.taskId=?", tcNum, term, clazzNum, taskId);
    }

    @Override
    public List<ClassEntity> getClazzByTcNumAndTerm(String tcNum, String term) {
        //noinspection unchecked
        List<CourseEntity> list = this.findByHql("from CourseEntity c where c.tcEntity.tcNum=? and c.term=?", tcNum, term);
        List<ClassEntity> clazz = new ArrayList<>();
        for (CourseEntity c : list){
            clazz.add(c.getClassEntity());
        }
        return clazz;
    }

    @Override
    public Map<ClassEntity, List<TaskEntity>> getClazzAndTaskByTcNumAndTerm(String tcNum, String term) {
        //noinspection unchecked
        List<CourseEntity> list = this.findByHql("from CourseEntity c where c.tcEntity.tcNum=? and c.term=?", tcNum, term);
        Map<ClassEntity, List<TaskEntity>> res = new HashMap<>(8);
        for (CourseEntity c : list){
            res.put(c.getClassEntity(), new ArrayList<>(c.getTasksFromCoz()));
        }
        return res;
    }
}
