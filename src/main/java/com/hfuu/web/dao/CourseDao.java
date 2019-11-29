package com.hfuu.web.dao;

import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.CourseEntity;

import java.util.List;

/**
 * @Description :
 * @date : 2019/9/25 18:45
 * @author : Ciel-08
 * 最后修改时间：2019年10月27日 23点04分
 * 最后修改人：whh0987@foxmail.com
 */
public interface CourseDao extends BaseDao<CourseEntity> {

    /***
     * 通过课程所属班级号、教师号来获取课程
     * @param classNum 班级号
     * @param tcNum 教师号
     * @param term 要查询的学期
     * @return 获得的课程实例
     */
    List<CourseEntity> getCourseByClassNumAndTcNumAndTerm(List<String> classNum, String tcNum, String term);
}
