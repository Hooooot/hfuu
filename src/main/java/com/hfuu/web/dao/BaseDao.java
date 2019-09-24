package com.hfuu.web.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 基础DAO接口
 * @author Ciel-08
 * 创建时间：${DATE} ${TIME}
 * 最后修改时间：
 * 最后修改人：
 *
 */
public interface BaseDao<T> {

    /**
     * 新增记录
     * @param t
     */
    void insert(T t);

    /**
     * 删除记录
     * @param t
     */
    void delete(T t);

    /**
     * 更新记录
     * @param t
     */
    void update(T t);

    /**
     * 根据id查找某条记录
     * @param id
     * @return
     */
    T findById(Class<T> c, Serializable id);

    /**
     * 根据id查找某条记录是否存在
     * @param id
     * @return
     */
    boolean isExist(Class<T> c, Serializable id);

    /**
     * 查询表中所有记录
     * @return
     */
    List<T> findAll(Class<T> c);

    /**
     * 查询表中有多少条记录
     * @param c
     * @return
     */
    Long count(Class<T> c);

    /**
     * 通过hql语句查询
     * @param hql
     * @return
     */
    List findByHql(String hql);

    /**
     * 通过hql语句查询（带参数）
     * @param hql
     * @param param
     * @return
     */
    List findByHql(String hql, Object... param);


}
