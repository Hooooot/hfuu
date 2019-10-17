package com.hfuu.web.service.teacher.impl;

import com.hfuu.web.dao.CourseDao;
import com.hfuu.web.dao.TeacherDao;
import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.CourseEntity;
import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.service.base.BaseServiceImpl;
import com.hfuu.web.service.teacher.TeacherControllerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private TeacherDao teacherDao;

    @Override
    public BaseDao getBaseDao() {
        return courseDao;
    }

    @Override
    public Map<String, List<CourseEntity>> getCourseByTeacherNum(String tcNum) {
        TeacherEntity tc = new TeacherEntity();
        tc.setTcNum(tcNum);
        List<CourseEntity> list= courseDao.findByHql("from CourseEntity c where c.tcEntity=?", tc);
        return this.groupByCozName(list);
    }

    @Override
    public TeacherEntity login(String name, String pw) {
        return teacherDao.getTeacherByNameAndPw(name, pw);
    }

    @Override
    public Map<String, List<CourseEntity>> groupByCozName(List<CourseEntity> list) {
        if (list == null){
            return null;
        }
        Map<String, List<CourseEntity>> map = new HashMap<>(5);
        for(CourseEntity c : list){
            if(map.containsKey(c.getCozName())){
                List<CourseEntity> li = map.get(c.getCozName());
                li.add(c);
                map.put(c.getCozName(), li);
            }else{
                List<CourseEntity> li = new ArrayList<>();
                li.add(c);
                map.put(c.getCozName(), li);
            }
        }
        return map;
    }
}
