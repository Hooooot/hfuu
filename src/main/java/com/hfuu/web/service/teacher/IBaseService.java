package com.hfuu.web.service.teacher;


import java.io.Serializable;
import java.util.List;

/**
 * @Description DAO层通用接口
 * @author Hooooot
 * @version 1.0
 * */
public interface IBaseService<T> extends Serializable {
    void save(T entity);
    void removeById(int id);
    T getById(int id);
    List getAll();
    void updateById(T entity);
    boolean isExist(T entity);
    boolean isExistById(int id);
}
