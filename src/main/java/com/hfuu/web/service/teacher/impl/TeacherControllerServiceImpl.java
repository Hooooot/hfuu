package com.hfuu.web.service.teacher.impl;

import com.hfuu.web.dao.CourseDao;
import com.hfuu.web.dao.TaskDao;
import com.hfuu.web.dao.TeacherDao;
import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.*;
import com.hfuu.web.others.utils.TermUtils;
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
    public List<CourseEntity> getCourseByClassNumAndTcNumDuringThisTerm(List<String> classNum, String tcNum) {
        String term = TermUtils.getCurrentTerm();
        return courseDao.getCourseByClassNumAndTcNumAndTerm(classNum, tcNum, term);
    }

    @Override
    public Map<String, List<StudentEntity>> getStudentsGroupByClazz(List<StudentEntity> student) {
        Map<String, List<StudentEntity>> clazz = new HashMap<>(5);
        for (StudentEntity s : student){
            String clazzNum = s.getClassEntity().getClassNum();
            if(clazz.containsKey(clazzNum)){
                List<StudentEntity> st = clazz.get(clazzNum);
                st.add(s);
                clazz.put(clazzNum, st);
            }else{
                List<StudentEntity> st = new ArrayList<>();
                st.add(s);
                clazz.put(clazzNum, st);
            }
        }
        return clazz;
    }

    @Override
    public List<StudentEntity> getStudents(String tcNum, String term, String clazzNum, int taskId) {
        return teacherDao.getStudentsByTcNumAndTermAndClazzNumAndTaskid(tcNum, term, clazzNum, taskId);
    }

    @Override
    public List<ClassEntity> getClazzByTcNumAndTerm(String tcNum, String term) {
        return teacherDao.getClazzByTcNumAndTerm(tcNum, term);
    }

    @Override
    public Map<ClassEntity, List<TaskEntity>> getClazzAndTaskByTcNumAndTerm(String tcNum, String term) {
        return teacherDao.getClazzAndTaskByTcNumAndTerm(tcNum, term);
    }

    @Override
    public List<CourseEntity> getCourseByTeacherNum(String tcNum) {
        //noinspection unchecked
        return (List<CourseEntity>) courseDao.findByHql("from CourseEntity c where c.tcEntity.tcNum=?", tcNum);
    }

    @Override
    public List<CourseEntity> getCourseByTeacherNumAndTerm(String tcNum, String term) {
        //noinspection unchecked
        return (List<CourseEntity>) courseDao.findByHql("from CourseEntity c where c.tcEntity.tcNum=? and c.term=?",
                                                        tcNum, term);
    }

    @Override
    public TeacherEntity login(String name, String pw) {
        return teacherDao.getTeacherByNameAndPw(name, pw);
    }

    @Override
    public CourseEntity getCourseByCozNumAndTcNum(String cozNum, TeacherEntity tc){
        List list = courseDao.findByHql("from CourseEntity c where c.tcEntity=? and c.cozNum=?", tc, cozNum);
        return list==null?null:(CourseEntity) list.get(0);
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
