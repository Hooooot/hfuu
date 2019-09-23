package com.hfuu.web.service.teacher;

import com.hfuu.web.entity.TeacherEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherService {
    int addTeacher(TeacherEntity teacherEntity);
    boolean isExist(TeacherEntity teacherEntity);
}
