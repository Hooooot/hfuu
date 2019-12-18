package com.hfuu.web.service.teacher;

import com.hfuu.web.entity.*;
import com.hfuu.web.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @author : whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 * @Description : Teacher的Controller层service
 * @date : 2019/10/04 18:49
 */
public interface TeacherControllerService extends BaseService {

    /**
     * 获取该教师号下所有的课程
     *
     * @param tcNum 教师号
     * @return CourseEntity类型的List
     */
    List<CourseEntity> getCourseByTeacherNum(String tcNum);

    /**
     * 获取该教师号下所有的课程
     *
     * @param tcNum 教师号
     * @param term  学期
     * @return CourseEntity类型的List
     */
    List<CourseEntity> getCourseByTeacherNumAndTerm(String tcNum, String term);

    /**
     * 通过教师用户名、密码获取教师信息
     *
     * @param name 教师姓名
     * @param pw   教师密码
     * @return Map内包含按cozNum分组的courseList，key为cozNum，value为所有cozNum相同的CourseEntity类型的List
     */
    TeacherEntity login(String name, String pw);

    /**
     * 通过课程名来对CourseEntity类型的List进行切片
     *
     * @param list 要切片的列表
     * @return 按cozName分组的 Map<String, List<CourseEntity>>
     */
    Map<String, List<CourseEntity>> groupByCozName(List<CourseEntity> list);

    /**
     * 通过教师号和学期获取课程
     *
     * @param tcNum 教师号
     * @param term  学期
     * @return 所有教师号为tcNum且学期为term的课程
     */
    List getCourseByTcNumAndTerm(String tcNum, String term);

    /**
     * 去除CourseEntity类型的List中重复的Class属性与Course属性，并将这两个属性按Map<String, List>返回
     *
     * @param list 要去重的list
     * @return Map<String, List> 长度为2，分别为classList、cozList，List则为这两个实体类的列表（无重复值）
     */
    Map<String, List> duplicateRemoval(List<CourseEntity> list);

    /**
     * 通过教师号和课程号来获取课程
     *
     * @param cozNum 课程号
     * @param tc     带有教师号的实例
     * @return 获取到的课程
     */
    CourseEntity getCourseByCozNumAndTcNum(String cozNum, TeacherEntity tc);

    /**
     * 向数据库中插入Task实体
     *
     * @param t 待写入的实体
     */
    void insertTask(TaskEntity t);

    /***
     * 通过课程所属班级号、教师号来获取课程
     * @param classNum 班级号
     * @param tcNum 教师号
     * @return 获得的课程实例
     */
    List<CourseEntity> getCourseByClassNumAndTcNumDuringThisTerm(List<String> classNum, String tcNum);

    /**
     * 将学生按班级分组
     *
     * @param student 要分组的学生
     * @return 分过组的学生
     */
    Map<String, List<StudentEntity>> getStudentsGroupByClazz(List<StudentEntity> student);

    /**
     * 通过教师号、学期获取该教师下所有的学生
     *
     * @param tcNum    教师号
     * @param term     学期
     * @param clazzNum 班级号
     * @param taskId   任务ID
     * @return 获取到的学生
     */
    List<StudentEntity> getStudents(String tcNum, String term, String clazzNum, int taskId);

    /**
     * 通过教师号和学期查询该教师号所带的所有班级
     *
     * @param tcNum 教师号
     * @param term  学期
     * @return 该教师在该学期带的所有班级
     */
    List<ClassEntity> getClazzByTcNumAndTerm(String tcNum, String term);

    /**
     * 通过教师号和学期查询该教师号所带的所有班级和该班级下的报告
     *
     * @param tcNum 教师号
     * @param term  学期
     * @return 该教师在该学期带的所有班级与报告Map<ClassEntity, List < TaskEntity>>
     */
    Map<ClassEntity, List<TaskEntity>> getClazzAndTaskByTcNumAndTerm(String tcNum, String term);
}
