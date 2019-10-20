package com.hfuu.web.service.impl;

import com.hfuu.web.dao.TaskDao;
import com.hfuu.web.dao.base.BaseDao;
import com.hfuu.web.entity.TaskEntity;
import com.hfuu.web.service.TaskService;
import com.hfuu.web.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description :
 * @date : 2019/9/25 19:03
 * @author : Ciel-08
 * 最后修改时间：2019年10月19日 23点19分
 * 最后修改人：whh0987@foxmail.com
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

    @Override
    public Map<String, Object> getTaskClosedAndNotClosedCount(Set taskSet) {
        Map<String, Object> map = new HashMap<>(2);
        if (taskSet==null){
            map.put("closed", 0);
            map.put("notClosed", 0);
            return map;
        }
        Date date = new Date();
        int closed = 0;
        int notClosed = 0;
        for (Object task : taskSet){
            Timestamp dead = ((TaskEntity)task).getDeadline();
            if(dead == null){
                continue;
            }
            if(date.before(dead)){
                notClosed++;
            }else{
                closed++;
            }
        }
        map.put("closed", closed);
        map.put("notClosed", notClosed);
        return map;
    }
}
