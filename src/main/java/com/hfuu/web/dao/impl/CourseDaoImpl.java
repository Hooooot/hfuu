package com.hfuu.web.dao.impl;

import com.hfuu.web.dao.CourseDao;
import com.hfuu.web.dao.base.BaseDaoImpl;
import com.hfuu.web.entity.CourseEntity;
import com.hfuu.web.entity.TeacherEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description :
 * @date :2019/9/25 18:53
 * @author :Ciel-08
 * 最后修改时间：2019年10月27日 23点04分
 * 最后修改人：whh0987@foxmail.com
 */
@Repository("courseDao")
public class CourseDaoImpl extends BaseDaoImpl<CourseEntity> implements CourseDao {
}
