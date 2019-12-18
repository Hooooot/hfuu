package com.hfuu.web.service;

import com.hfuu.web.entity.SubmitEntity;
import com.hfuu.web.service.base.BaseService;

/**
 * @author : Ciel-08
 * 最后修改时间：2019/09/28 20:29
 * 最后修改人：Ciel-08
 * @Description : 提交记录服务接口
 * @date : 2019/9/25 19:06
 */
public interface SubmitService extends BaseService<SubmitEntity> {
    /**
     * 根据提交状态查询任务提交数目
     *
     * @param taskId 任务ID
     * @param state  提交状态：待提交、待批阅、已批阅
     * @return 指定任务下某种状态的提交数
     */
    Long countSubmitByState(int taskId, String state);

    /**
     * 根据ID更新分数
     *
     * @param id    submit的ID
     * @param score 要更新的分数
     * @return 更新的行数
     */
    int updateScoreById(int id, short score);

    /**
     * 根据ID更新分数和状态
     *
     * @param id    submit的ID
     * @param score 要更新的分数
     * @param state 要更新的状态
     * @return 更新成功的行数
     */
    int updateScoreAndStateById(int id, short score, String state);
}
