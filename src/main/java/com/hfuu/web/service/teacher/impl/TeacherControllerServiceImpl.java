package com.hfuu.web.service.teacher.impl;

import com.hfuu.web.dao.CourseDao;
import com.hfuu.web.dao.TaskDao;
import com.hfuu.web.dao.TeacherDao;
import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.ClassEntity;
import com.hfuu.web.entity.CourseEntity;
import com.hfuu.web.entity.TaskEntity;
import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.service.base.BaseServiceImpl;
import com.hfuu.web.service.teacher.TeacherControllerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = Exception.class)
public class TeacherControllerServiceImpl extends BaseServiceImpl implements TeacherControllerService {
    @Resource
    private CourseDao courseDao;
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private TaskDao taskDao;

    @Override
    public BaseDao getBaseDao() {
        return courseDao;
    }

    @Override
    public List getCourseByTcNumAndTerm(String tcNum, String term){
        TeacherEntity tc = new TeacherEntity();
        tc.setTcNum(tcNum);
        return findByHql("from CourseEntity c where c.TeacherEntity = ? and c.term=?", tc, term);
    }

    @Override
    public Map<String, List> duplicateRemoval(List<CourseEntity> list) {
        if (list == null){
            return null;
        }
        List<ClassEntity> classList = new ArrayList<>();
        List<CourseEntity> cozList = new ArrayList<>();
        for(CourseEntity c : list){
            boolean exist = false;
            for(CourseEntity courseEntity : cozList){
                if(c.getCozNum().equals(courseEntity.getCozNum())){
                    exist = true;
                    break;
                }
            }
            if(!exist){
                cozList.add(c);
            }
            ClassEntity clazz = c.getClassEntity();
            if(!classList.contains(clazz)){
                classList.add(clazz);
            }
        }
        Map<String, List> map = new HashMap<>(2);
        map.put("classList", classList);
        map.put("cozList", cozList);
        return map;
    }

    @Override
    public void insertTask(TaskEntity t) {
        taskDao.insert(t);
    }

    @Override
    public List<CourseEntity> getCourseByTeacherNum(String tcNum) {
        TeacherEntity tc = new TeacherEntity();
        tc.setTcNum(tcNum);
        List<CourseEntity> list= courseDao.findByHql("from CourseEntity c where c.tcEntity=?", tc);
        return list;
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
