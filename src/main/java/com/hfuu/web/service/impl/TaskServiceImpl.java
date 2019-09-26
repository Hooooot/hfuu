package com.hfuu.web.service.impl;

import com.hfuu.web.dao.TaskDao;
import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.TaskEntity;
import com.hfuu.web.service.TaskService;
import com.hfuu.web.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Decription :
 * @CreateDate : 2019/9/25 19:03
 * @author : Ciel-08
 * 最后修改时间：
 * 最后修改人：
 */
@Service("taskService")
@Transactional(rollbackFor = Exception.class)
public class TaskServiceImpl extends BaseServiceImpl<TaskEntity> implements TaskService {

    @Resource
    private TaskDao taskDao;

    @Override
    public BaseDao<TaskEntity> getBaseDao() {
        return taskDao;
    }
}
