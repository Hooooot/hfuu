package com.hfuu.web.dao;

import com.hfuu.web.dao.impl.TeacherDaoImpl;

import java.io.Serializable;
import java.util.List;

/**
 * @Description DAO层通用接口
 * @author Hooooot
 * @version 1.0
 * */
public interface IBaseDao<T> extends Serializable {
    /**
     * 插入特定的行
     * @param entity:entity实例
     * */
    void insert(T entity);
    /**
     * 通过id更新特定的行
     * @param entity :通过entity的id更新entity其他的数据（全覆盖）
     * */
    void updateById(T entity);
    /**
     * 删除特定的行
     * @param id :待删除entity的id
     * */
    void deleteById(int id);
    /**
     * 获取特定的行
     * 实现该方法时，应至少保证可以通过entity的id来查找。
     * 其他可选实现包括不限于：帐号+密码、学号、教师号等。
     * @see TeacherDaoImpl#select
     * @param entity: entity实例
     * @return List<>: entity列表
     * */
    List<T> select(T entity);
    /**
     * 获取表中所有的行
     * @return List<>:所有entity的列表
     * */
    List<T> getAll();
    /**
     * 是否存在该entity实例
     * 实现该方法时，应至少保证可以通过entity的id来查找。
     * 其他可选实现包括不限于：帐号+密码、学号、教师号等。
     * @see TeacherDaoImpl#isExist
     * @param entity:entity实例
     * @return true代表存在，false代表不存在
     * */
    boolean isExist(T entity);
}
