package com.hfuu.web.service.impl;

import com.hfuu.web.dao.TeacherDao;
import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.service.TeacherService;
import com.hfuu.web.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : Ciel-08
 * 最后修改时间：
 * 最后修改人：
 * @Description :
 * @date : 2019/9/25 19:03
 */
@Service("teacherService")
@Transactional(rollbackFor = Exception.class)
public class TeacherServiceImpl extends BaseServiceImpl<TeacherEntity> implements TeacherService {

    @Resource
    private TeacherDao teacherDao;

    @Override
    public BaseDao<TeacherEntity> getBaseDao() {
        return teacherDao;
    }

    @Override
    public void update(String tcNum, String tcName, String tcSex, String tcPhone, String tcEmail) {
        teacherDao.executeHql("update TeacherEntity tc set tc.tcName=?,tc.tcSex=?,tc.tcPhone=?,tc.tcEmail=? where tc.tcNum=?",
                tcName, tcSex, tcPhone, tcEmail, tcNum);
    }

    @Override
    public void update(String tcNum, String tcAvatar) {
        teacherDao.executeHql("update TeacherEntity tc set tc.tcAvatar=? where tc.tcNum=?",
                tcAvatar, tcNum);
    }

    @Override
    public TeacherEntity getTeacherByTcNumAndTcEmail(String tcNum, String tcEmail) {
        //noinspection unchecked
        List<TeacherEntity> res = teacherDao.findByHql("from TeacherEntity tc where tc.tcNum=? and tc.tcEmail=?", tcNum, tcEmail);
        if(res.size()>0){
            return res.get(0);
        }
        return null;
    }

    @Override
    public boolean updatePassword(String tcNum, String pwd) {
        return teacherDao.executeHql("update TeacherEntity tc set tc.tcPw=? where tc.tcNum=?", pwd, tcNum) > 0;
    }
}
