package com.hfuu.web.service;

import com.hfuu.web.entity.SubmitEntity;
import com.hfuu.web.service.base.BaseService;

/**
 * @Decription : 提交记录服务接口
 * @CreateDate : 2019/9/25 19:06
 * @author : Ciel-08
 * 最后修改时间：2019/09/28 20:29
 * 最后修改人：Ciel-08
 */
public interface SubmitService extends BaseService<SubmitEntity> {
    /**
     * 根据提交状态查询任务提交数目
     * @param taskId 任务ID
     * @param state  提交状态：待提交、待批阅、已批阅
     * @return  指定任务下某种状态的提交数
     */
    Long countSubmitByState(int taskId, String state);
}
