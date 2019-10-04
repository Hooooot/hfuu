package com.hfuu.web.service.teacher.impl;

import com.hfuu.web.dao.CourseDao;
import com.hfuu.web.dao.DepDao;
import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.service.base.BaseServiceImpl;
import com.hfuu.web.service.teacher.TeacherControllerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description : Teacher的Controller层service实现
 * @date : 2019/10/04 18:49
 * @author : whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 */
@Service("teacherControllerService")
public class TeacherControllerServiceImpl extends BaseServiceImpl implements TeacherControllerService {
    @Resource
    private CourseDao courseDao;

    @Override
    public BaseDao getBaseDao() {
        return courseDao;
    }

    @Override
    public Map getCourseByTeacherNum(String tcNum) {
        TeacherEntity tc = new TeacherEntity();
        tc.setTcNum(tcNum);
        List list= courseDao.findByHql("from CourseEntity c where c.tcEntity=?", tc);
        Map<String, Object> map = new HashMap<>(1);
        map.put("result", list);
        return map;
    }
}
