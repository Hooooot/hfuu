package com.hfuu.web.service;

import com.hfuu.web.entity.DepartmentEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 机构Service类
 * @author Ciel-08
 * 创建时间：2019/9/24 10:25
 * 最后修改时间：
 * 最后修改人：
 */

@Service
public interface DepService {
    void insert(DepartmentEntity d);
    void delete(DepartmentEntity d);
    void update(DepartmentEntity d);
    DepartmentEntity findById(Serializable id);
    boolean isExist(Serializable id);
    List<DepartmentEntity> findAll();
    Long count();
    List findByHql(String hql);
}
