package com.hfuu.web.service.teacher.impl;

import com.hfuu.web.dao.ITeacherDao;
import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.service.teacher.ITeacherService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("teacherService")
public class TeacherServiceImpl implements ITeacherService {
    static private Logger log = Logger.getLogger(TeacherServiceImpl.class);
    @Resource
    private ITeacherDao<TeacherEntity> teacherDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Object entity) {
        teacherDAO.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(int id) {
        teacherDAO.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object getById(int id) {
        TeacherEntity e = new TeacherEntity();
        e.setTcId(id);
        return teacherDAO.select(e).get(0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List getAll() {
        return teacherDAO.getAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(Object entity) {
        teacherDAO.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isExist(Object entity) {
        return teacherDAO.isExist(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isExistById(int id) {
        TeacherEntity e = new TeacherEntity();
        e.setTcId(id);
        return teacherDAO.select(e).size() > 0;
    }

    public ITeacherDao<TeacherEntity> getTeacherDAO() {
        return teacherDAO;
    }

    public void setTeacherDAO(ITeacherDao<TeacherEntity> teacherDAO) {
        this.teacherDAO = teacherDAO;
    }
}
