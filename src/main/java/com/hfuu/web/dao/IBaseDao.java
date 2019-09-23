package com.hfuu.web.dao;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description DAO层通用接口
 * @author Hooooot
 * @version 1.0
 * */
@Transactional(rollbackFor = Exception.class)
public interface IBaseDao<T>{
    /**
     * 插入特定的行
     * @param entity:entity实例
     * @return true代表插入成功，false代表失败
     * */
    boolean insert(T entity);
    /**
     * 更新特定的行
     * @param entity:entity实例
     * @return true代表更新成功，false代表失败
     * */
    boolean update(T entity);
    /**
     * 删除特定的行
     * @param entity:entity实例
     * @return true代表删除成功，false代表失败
     * */
    boolean delete(T entity);
    /**
     * 获取特定的行
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
     * @param entity:entity实例
     * @return true代表存在，false代表不存在
     * */
    boolean isExist(T entity);
}
