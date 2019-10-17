package com.hfuu.web.dao;

import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.TeacherEntity;

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
}
