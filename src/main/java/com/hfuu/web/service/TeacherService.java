package com.hfuu.web.service;

import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.service.base.BaseService;

/**
 * @author : Ciel-08
 * 最后修改时间：
 * 最后修改人：
 * @Description : 教师服务接口
 * @date : 2019/9/25 19:07
 */
public interface TeacherService extends BaseService<TeacherEntity> {
    /**
     * 通过教师的工号来更新教师的姓名、性别、电话号码、邮箱
     * @param tcNum 教师的工号
     * @param tcName 教师的姓名
     * @param sex 教师的性别
     * @param tcPhone 教师的电话
     * @param tcEmail 教师的邮箱
     * */
    void update(String tcNum, String tcName, String sex, String tcPhone, String tcEmail);

    /**
     * 通过教师的工号来更新教师的头像
     * @param tcNum 教师的工号
     * @param tcAvatar 教师的头像在服务器中的位置
     * */
    void update(String tcNum, String tcAvatar);

    /**
     * 通过教师的工号和教师邮箱来查找教师
     * @param tcNum 教师的工号
     * @param tcEmail 教师的邮箱
     * @return 查询到的教师
     * */
    TeacherEntity getTeacherByTcNumAndTcEmail(String tcNum, String tcEmail);

    /**
     * 更新教师的密码
     * @param tcNum 教师工号
     * @param pwd 新密码
     * @return 是否更新成功
     * */
    boolean updatePassword(String tcNum, String pwd);
}
