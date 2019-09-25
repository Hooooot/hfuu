package com.hfuu.web.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：基础Dao接口
 *
 * @author: Ciel-08
 * 创建时间：2019/9/25 18:53
 * 最后修改时间：
 * 最后修改人：
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
    T findById(Serializable id);

    /**
     * 根据id查找某条记录是否存在
     * @param id
     * @return
     */
    boolean isExist(Serializable id);

    /**
     * 查询表中所有记录
     * @return
     */
    List findAll();

    /**
     * 查询表中有多少条记录
     * @return
     */
    Long count();

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
