package com.hfuu.web.dao;

import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.SubmitEntity;

/**
 * @Description :
 * @date : 2019/9/25 18:45
 * @author : Ciel-08
 * 最后修改时间：2019/9/28 20:20
 * 最后修改人：Ciel-08
 */
public interface SubmitDao extends BaseDao<SubmitEntity> {
    /**
     * 根据提交状态查询任务提交数目
     * @param taskId 任务ID
     * @param state  提交状态：待提交、待批阅、已批阅
     * @return  指定任务下某种状态的提交数
     */
    Long countSubmitByState(int taskId, String state);
}
