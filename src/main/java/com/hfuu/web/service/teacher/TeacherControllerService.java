package com.hfuu.web.service.teacher;

import com.hfuu.web.entity.CourseEntity;
import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @Description : Teacher的Controller层service
 * @date : 2019/10/04 18:49
 * @author : whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 */
public interface TeacherControllerService extends BaseService {

    /**
     * 获取该教师号下所有的课程
     *
     * @param tcNum 教师号
     * @return Map内包含按cozNum分组的courseList，key为cozNum，value为所有cozNum相同的CourseEntity类型的List
     */
    Map<String, List<CourseEntity>> getCourseByTeacherNum(String tcNum);

    /**
     * 通过教师用户名、密码获取教师信息
     *
     * @param name 教师姓名
     * @param pw  教师密码
     * @return Map内包含按cozNum分组的courseList，key为cozNum，value为所有cozNum相同的CourseEntity类型的List
     * */
    TeacherEntity login(String name, String pw);

    /**
     * 通过课程名来对CourseEntity类型的List进行切片
     *
     * @param list 要切片的列表
     * @return 按cozName分组的 Map<String, List<CourseEntity>>
     * */
    Map<String, List<CourseEntity>> groupByCozName(List<CourseEntity> list);
}
