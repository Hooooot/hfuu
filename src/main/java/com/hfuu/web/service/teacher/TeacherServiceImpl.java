package com.hfuu.web.service.teacher;

import com.hfuu.web.dao.ITeacherDao;
import com.hfuu.web.entity.TeacherEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("teacherService")
public class TeacherServiceImpl implements ITeacherService {
    static private Logger log = Logger.getLogger(TeacherServiceImpl.class);
    @Resource()
    private ITeacherDao dao;
    @Override
    public int addTeacher(TeacherEntity teacherEntity) {
        log.info("teacher service impl");
        return 0;
    }

    @Override
    public boolean isExist(TeacherEntity teacherEntity) {
        return dao.isExist(teacherEntity);
    }

    public ITeacherDao getDao() {
        return dao;
    }

    public void setDao(ITeacherDao dao) {
        this.dao = dao;
    }
}
