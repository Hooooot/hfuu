package com.hfuu.web.dao;

import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.ClassEntity;
import com.hfuu.web.entity.StudentEntity;
import com.hfuu.web.entity.TaskEntity;
import com.hfuu.web.entity.TeacherEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @date : 2019/9/25 18:45
 * @author : Ciel-08
 * 最后修改时间：
 * 最后修改人：
 */
public interface TeacherDao extends BaseDao<TeacherEntity> {
    /**
     * 通过教师用户名、密码获取教师信息
     *
     * @param name 教师用户名
     * @param pw 教师密码
     * @return TeacherEntity 登录成功，则返回相关教师信息，否则为null
     * */
    TeacherEntity getTeacherByNameAndPw(String name, String pw);

    /**
     * 通过教师号、学期、班级获取该教师下所有的学生
     * @param tcNum 教师号
     * @param term 学期
     * @param clazzNum 班级号
     * @return 获取到的学生
     * */
    List<StudentEntity> getStudentsByTcNumAndTermAndClazzNum(String tcNum, String term, String clazzNum);

    /**
     * 通过教师号、学期、班级获取该教师下所有的学生
     * @param tcNum 教师号
     * @param term 学期
     * @param clazzNum 班级号
     * @param taskId 任务ID
     * @return 获取到的学生
     * */
    List<StudentEntity> getStudentsByTcNumAndTermAndClazzNumAndTaskid(String tcNum, String term, String clazzNum, int taskId);

    /**
     * 通过教师号和学期查询该教师号所带的所有班级
     * @param tcNum 教师号
     * @param term 学期
     * @return 该教师在该学期带的所有班级
     * */
    List<ClassEntity> getClazzByTcNumAndTerm(String tcNum, String term);

    /**
     * 通过教师号和学期查询该教师号所带的所有班级和该班级下的报告
     * @param tcNum 教师号
     * @param term 学期
     * @return 该教师在该学期带的所有班级与报告Map<ClassEntity, List<TaskEntity>>
     * */
    Map<ClassEntity, List<TaskEntity>> getClazzAndTaskByTcNumAndTerm(String tcNum, String term);
}
