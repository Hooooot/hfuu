package com.hfuu.web.dao.impl;

import com.hfuu.web.dao.TaskDao;
import com.hfuu.web.dao.base.BaseDaoImpl;
import com.hfuu.web.entity.TaskEntity;
import org.springframework.stereotype.Repository;

/**
 * 描述：
 *
 * @author: Ciel-08
 * 创建时间：2019/9/25 19:01
 * 最后修改时间：
 * 最后修改人：
 */
@Repository("taskDao")
public class TaskDaoImpl extends BaseDaoImpl<TaskEntity> implements TaskDao {
}
