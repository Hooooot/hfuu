package com.hfuu.web.service;

import com.hfuu.web.entity.TaskEntity;
import com.hfuu.web.service.base.BaseService;

import java.util.Map;
import java.util.Set;

/**
 * @Description : 任务服务接口
 * @date : 2019/9/25 19:07
 * @author : Ciel-08
 * 最后修改时间：
 * 最后修改人：
 */
public interface TaskService extends BaseService<TaskEntity> {
    /**
     * 获取taskSet中截止提交与未截止提交的数量
     *
     * @param taskSet task集合
     * @return Map,用于存放截止与未截止的数量，其中截止数量的key为："closed"，未截止数量的key为："notClosed"。
     * */
    Map<String, Object> getTaskClosedAndNotClosedCount(Set taskSet);
}
