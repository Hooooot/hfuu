package com.hfuu.web.service.teacher;

import com.hfuu.web.service.base.BaseService;

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
     * */
    Map getCourseByTeacherNum(String tcNum);
}
