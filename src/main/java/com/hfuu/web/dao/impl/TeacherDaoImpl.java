package com.hfuu.web.dao.impl;

import com.hfuu.web.dao.TeacherDao;
import com.hfuu.web.dao.base.BaseDaoImpl;
import com.hfuu.web.entity.TeacherEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description :
 * @date :2019/9/25 19:01
 * @author :Ciel-08
 * 最后修改时间：2019年10月8日 17点05分
 * 最后修改人：whh0987@foxmail.com
 */
@Repository("teacherDao")
public class TeacherDaoImpl extends BaseDaoImpl<TeacherEntity> implements TeacherDao {
    @Override
    public TeacherEntity getTeacherByNameAndPw(String name, String pw) {
        TeacherEntity tc = new TeacherEntity();
        tc.setTcName(name);
        tc.setTcPw(pw);
        List list = this.findByHql("from TeacherEntity tc where tc.tcName=? and tc.tcPw=?", name, pw);
        if (list.size() != 0){
            return (TeacherEntity) list.get(0);
        }
        return null;
    }
}
